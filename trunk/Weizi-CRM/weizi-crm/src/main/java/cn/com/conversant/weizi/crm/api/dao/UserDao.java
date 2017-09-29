package cn.com.conversant.weizi.crm.api.dao;

import cn.com.conversant.weizi.crm.api.entity.User;
import cn.com.conversant.weizi.crm.commons.annotation.DataAccessRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@DataAccessRepository
public interface UserDao {
    List<User> getUserByType(int type);

    List<User> getUserByNotType(int type);

    User getUserByName(String username);

    User getUserById(long userId);

    void addUser(User user);

    List<User> list(@Param("offset") int offset, @Param("length") int length);

    int getUserTotalCount();

    List<User> searchUser(@Param("searchKey") String searchKey, @Param("offset") int offset, @Param("length") int length);

    int getSearchUserTotalCount(@Param("searchKey") String searchKey);

    void deleteById(long userId);

    void update(User user);
}