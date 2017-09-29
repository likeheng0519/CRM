package cn.com.conversant.weizi.crm.api.service.impl;

import cn.com.conversant.weizi.crm.api.constans.HasPermission;
import cn.com.conversant.weizi.crm.api.dao.UserPermissionDao;
import cn.com.conversant.weizi.crm.api.dto.UserPermissionJson;
import cn.com.conversant.weizi.crm.api.entity.UserPermission;
import cn.com.conversant.weizi.crm.api.service.UserPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by conversant on 2017/1/9.
 */
@Service("userPermissionService")
public class UserPermissionServiceImpl implements UserPermissionService {
    @Resource
    private UserPermissionDao userPermissionDao;

    public void addUserPermission(UserPermission userPermission) {
        userPermissionDao.addProjectUserPermission(userPermission);
    }

    @Transactional
    public List<UserPermission> getListByUserId(long userId) {
        return userPermissionDao.getUserPermissionListByUserId(userId);

    }
    @Transactional
    public List<UserPermission> getListAll() {
        return userPermissionDao.getUserPermissionListAll();
    }

    public void setUserPermissionDao(UserPermissionDao userPermissionDao) {
        this.userPermissionDao = userPermissionDao;
    }

    @Transactional
    public void deleteByMetadataId(long metadata_id) {
        userPermissionDao.deleteUserPermissionByMetadataId(metadata_id);
    }

    @Transactional
    public List<UserPermission> getAllCustomerPermissionList() {
        return userPermissionDao.getAllCustomerPermissionList();
    }

    @Transactional
    public void updateCustomerPermissionList(List<UserPermissionJson> list) {
        if(list==null){
            return ;
        }

        for(int i=0;i<list.size();i++){
            UserPermission item=new UserPermission();
            item.setId(list.get(i).getUser_permission_id());
            item.setUser_id(list.get(i).getUser_id());
            item.setProject_metadata_id(list.get(i).getMetadata_id());
            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("metadataId",item.getProject_metadata_id());
            map.put("userId",item.getUser_id());
            UserPermission temp=userPermissionDao.getUserPermissionByUserIdAndMetadataId(map);
            if(temp==null&&list.get(i).getHasPermission()== HasPermission.YesPermission.getCode()){
                userPermissionDao.addProjectUserPermission(item);
            }else if(temp!=null&&list.get(i).getHasPermission()== HasPermission.NoPermission.getCode()){
                userPermissionDao.deleteByUserIdAndMetadataId(map);
            }
        }
    }
}
