package cn.com.conversant.weizi.crm.api.shiro;

import cn.com.conversant.commons.exception.BaseRuntimeException;
import cn.com.conversant.weizi.crm.api.entity.User;
import cn.com.conversant.weizi.crm.api.exception.AdminErrorCode;
import cn.com.conversant.weizi.crm.api.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: huangli@conversant.com.cn
 * Date: 14-2-19
 * Time: 下午2:12
 * To change this template use File | Settings | File Templates.
 */
@Service("shiroDbRealm")
public class ShiroDbRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Override
    public String getName() {
        return getClass().getName();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        User user = (User) principals.getPrimaryPrincipal();
        if (user == null) {
            throw new BaseRuntimeException(AdminErrorCode.ADMIN_ACCOUNT_NO_EXISTED);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>();
        Set<String> permissions = new HashSet<String>();

        roles.add(String.valueOf(user.getUserType()));

        info.addRoles(roles);
        info.addStringPermissions(permissions);
        logger.info("====================================================== roles==" + org.springframework.util.StringUtils.collectionToDelimitedString(roles, ","));
        logger.info("====================================================== permissions=="+ org.springframework.util.StringUtils.collectionToDelimitedString(permissions, ","));
        logger.info("===================Query Permissons===================" + user.getUsername());
        logger.info("======================================================");

        return info;
    }

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SimpleAuthenticationInfo info = null;

        UsernamePasswordToken authToken = (UsernamePasswordToken) token;
        authToken.setRememberMe(authToken.isRememberMe());

        String username = authToken.getUsername();
        String password = new String(authToken.getPassword());

        User user=null;
        try{
            user = userService.login(username, password);
        } catch(Exception e){
            BaseRuntimeException r=(BaseRuntimeException)e;
            throw new AuthenticationException(r.getMessage());
        }

        String password1 = user.getPassword();
        String[] encodedStr = StringUtils.split(password1, "$");
        info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(encodedStr[1]), getName());
        return info;
    }

    /**
     * set hash algorithm and iterations of password verification.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        AllowAllCredentialsMatcher matcher = new AllowAllCredentialsMatcher();
        setCredentialsMatcher(matcher);
    }

    public void clearCachedAuthorizationInfo(User user) {
        User realAdmin = user.copy();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(user, getName());
        clearCachedAuthorizationInfo(principals);
    }


}
