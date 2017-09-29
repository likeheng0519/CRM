/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 * <p/>
 * Created on 14-3-3.
 */
package cn.com.conversant.weizi.crm.api.mvc;

import cn.com.conversant.commons.exception.BaseRuntimeException;
import cn.com.conversant.weizi.crm.api.entity.User;
import cn.com.conversant.weizi.crm.api.exception.AdminErrorCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Created with Swift CMS
 * User: huangli@conversant.com.cn
 * Date: 14-3-3
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 */
public class UserRequiredResolver implements WebArgumentResolver

{
    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
        if (methodParameter.hasParameterAnnotation(UserRequired.class)) {
            return getUser(webRequest);
        }
        return null;
    }


    protected User getUser(NativeWebRequest webRequest) {
        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principals = subject.getPrincipals();
        if (principals == null || principals.isEmpty()) {
            //no identity - they're anonymous, not allowed:
            throw new BaseRuntimeException(AdminErrorCode.SESSION_INVALIDATE);
        } else {
            User user = (User) subject.getPrincipal();
            if (user == null) {
                throw new BaseRuntimeException(AdminErrorCode.SESSION_INVALIDATE);
            }
            return user;
        }

    }
}
