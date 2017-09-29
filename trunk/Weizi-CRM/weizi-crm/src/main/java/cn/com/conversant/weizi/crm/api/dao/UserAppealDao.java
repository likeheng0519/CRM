package cn.com.conversant.weizi.crm.api.dao;

import cn.com.conversant.weizi.crm.api.entity.UserAppeal;
import cn.com.conversant.weizi.crm.commons.annotation.DataAccessRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by conversant on 2017/1/10.
 */
@DataAccessRepository
public interface UserAppealDao {
    void addUserAppeal(UserAppeal userAppeal);

    UserAppeal getUserAppealById(long id);

    List<UserAppeal> getUserAppealByAuthor(long author_id);

    List<UserAppeal> getUserAppealByStoreCode(@Param("store_code") String store_code);

    int getAppealTotalCount();

    List<UserAppeal> list(@Param("offset") int offset, @Param("length") int length);

    void changeStatus(UserAppeal oldAppeal);

    int getSearchTotalCount(@Param("searchKey") String searchKey, @Param("projectId") long projectId);

    List<UserAppeal> searchAppeal(@Param("searchKey") String searchKey, @Param("projectId") long projectId,
                                  @Param("offset") int offset, @Param("length") int length);
}
