package cn.com.conversant.weizi.crm.api.dao;

import cn.com.conversant.weizi.crm.api.entity.UserPermission;
import cn.com.conversant.weizi.crm.commons.annotation.DataAccessRepository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by conversant on 2017/1/9.
 */
@DataAccessRepository
public interface UserPermissionDao {
    UserPermission getUserPermissionByUserIdAndMetadataId(HashMap<String,Object> map);
    void deleteByUserIdAndMetadataId(HashMap<String,Object> map);
    List<UserPermission> getUserPermissionListByUserId(long userId);
    List<UserPermission> getUserPermissionListAll();
    void deleteUserPermissionByMetadataId(long metadata_id);
    void addProjectUserPermission(UserPermission userPermission);
    List<UserPermission>  getAllCustomerPermissionList();
    void updateUserPermission(UserPermission userPermission);
}
