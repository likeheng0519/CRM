package cn.com.conversant.weizi.crm.api.service;

import cn.com.conversant.weizi.crm.api.dao.ProjectContentDao;
import cn.com.conversant.weizi.crm.api.dao.UserPermissionDao;
import cn.com.conversant.weizi.crm.api.dto.UserPermissionJson;
import cn.com.conversant.weizi.crm.api.entity.UserPermission;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by conversant on 2017/1/9.
 */
public interface UserPermissionService {
    void addUserPermission(UserPermission userPermission);
    List<UserPermission> getListByUserId(long userId);
    List<UserPermission> getListAll();
    void deleteByMetadataId(long metadata_id);
    List<UserPermission>  getAllCustomerPermissionList();
    void updateCustomerPermissionList(List<UserPermissionJson> list);
}
