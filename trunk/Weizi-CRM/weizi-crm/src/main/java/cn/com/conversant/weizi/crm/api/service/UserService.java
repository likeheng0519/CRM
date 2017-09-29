package cn.com.conversant.weizi.crm.api.service;

import cn.com.conversant.commons.paging.PagingList;
import cn.com.conversant.weizi.crm.api.entity.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void deleteUser(long userId);

    User getUserById(long id);

    List<User> getUserByType(int type);

    List<User> getUserByNotType(int type);

    User login(String username, String password);

    PagingList<User> searchUser(String searchItem, int pageNumber, int pageSize);

    PagingList<User> listUser(int pageNumber, int pageSize);

    void editUser(long userId, int userType, String oldPassword, String newPassword);
}
