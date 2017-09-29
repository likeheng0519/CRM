package cn.com.conversant.weizi.crm.api.service.impl;

import cn.com.conversant.commons.crypto.EncryptionType;
import cn.com.conversant.commons.crypto.EncryptionUtil;
import cn.com.conversant.commons.db.SQLHelper;
import cn.com.conversant.commons.exception.BaseRuntimeException;
import cn.com.conversant.commons.paging.PagingArrayList;
import cn.com.conversant.commons.paging.PagingList;
import cn.com.conversant.weizi.crm.api.dao.UserDao;
import cn.com.conversant.weizi.crm.api.entity.User;
import cn.com.conversant.weizi.crm.api.exception.AdminErrorCode;
import cn.com.conversant.weizi.crm.api.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
	public List<User> getUserByType(int type) {
		return this.userDao.getUserByType(type);
	}
    @Transactional
    public List<User> getUserByNotType(int type) {
        return this.userDao.getUserByNotType(type);
    }

    @Transactional
    public void addUser(User user) {

        String name = user.getUsername();
        if (StringUtils.isBlank(name)) {
            throw new BaseRuntimeException(AdminErrorCode.ADMIN_ACCOUNT_INVALID);
        }
        name = name.trim();
        name = name.toLowerCase();
        User oldUser = userDao.getUserByName(name);

        if (oldUser!=null) {
            throw new BaseRuntimeException(AdminErrorCode.ADMIN_ACCOUNT_EXISTED);
        }

        String password = user.getPassword();
        String encryptPassword = EncryptionUtil.encryptPassword(EncryptionType.SHA1, password);

        user.setPassword(encryptPassword);

        userDao.addUser(user);
    }

    @Transactional
    public void deleteUser(long userId) {
        userDao.deleteById(userId);
    }

    @Transactional
    public User login(String username, String password) {
        if (StringUtils.isBlank(username)) {
            throw new BaseRuntimeException(AdminErrorCode.ADMIN_ACCOUNT_NO_EXISTED);
        }
        username = username.trim();
        username = username.toLowerCase();

        User user = userDao.getUserByName(username);
        if (user == null) {
            throw new BaseRuntimeException(AdminErrorCode.ADMIN_ACCOUNT_NO_EXISTED);
        }

        boolean isCorrectPwd = EncryptionUtil.checkEncPassword(password, user.getPassword());
        if (!isCorrectPwd) {
            throw new BaseRuntimeException(AdminErrorCode.ADMIN_INVALID_PASSWORD);
        }

        return user;
    }

    @Transactional
    public PagingList<User> searchUser(String searchItem, int pageNumber, int pageSize) {
        int offset = -1;

        if (pageSize > 0) {
            offset = (pageNumber - 1) * pageSize;
        }

        String realSearchKey = SQLHelper.escapeQueryChars(searchItem);

        int totalCount = userDao.getSearchUserTotalCount(realSearchKey);
        List<User> userList = userDao.searchUser(realSearchKey, offset, pageSize);

        return new PagingArrayList<User>(pageNumber, pageSize, totalCount, userList);
    }

    @Transactional
    public PagingList<User> listUser(int pageNumber, int pageSize) {
        int offset = -1;

        if (pageSize > 0) {
            offset = (pageNumber - 1) * pageSize;
        }

        int totalCount = userDao.getUserTotalCount();
        List<User> userList = userDao.list(offset, pageSize);

        return new PagingArrayList<User>(pageNumber, pageSize, totalCount, userList);
    }

    @Transactional
    public void editUser(long userId, int userType, String oldPassword, String newPassword) {
        User user = userDao.getUserById(userId);
        if (user == null) {
            throw new BaseRuntimeException(AdminErrorCode.ADMIN_ACCOUNT_NO_EXISTED);
        }

        if(oldPassword!=""&&newPassword!=""){
            boolean isCorrectPwd = EncryptionUtil.checkEncPassword(oldPassword, user.getPassword());
            if (!isCorrectPwd) {
                throw new BaseRuntimeException(AdminErrorCode.ADMIN_INVALID_PASSWORD);
            }

            String encryptPassword = EncryptionUtil.encryptPassword(EncryptionType.SHA1, newPassword);
            user.setPassword(encryptPassword);
        }

        user.setUserType(userType);

        userDao.update(user);
    }

}
