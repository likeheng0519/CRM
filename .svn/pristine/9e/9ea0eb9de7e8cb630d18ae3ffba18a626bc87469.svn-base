package cn.com.conversant.weizi.crm.api.service.impl;

import cn.com.conversant.weizi.crm.api.constans.UserType;
import cn.com.conversant.weizi.crm.api.dao.ProjectContentDao;
import cn.com.conversant.weizi.crm.api.dao.ProjectMetadataDao;
import cn.com.conversant.weizi.crm.api.dao.ProjectSearchConditionDao;
import cn.com.conversant.weizi.crm.api.entity.ProjectContent;
import cn.com.conversant.weizi.crm.api.entity.ProjectMetadata;
import cn.com.conversant.weizi.crm.api.entity.User;
import cn.com.conversant.weizi.crm.api.entity.UserPermission;
import cn.com.conversant.weizi.crm.api.service.MetadataService;
import cn.com.conversant.weizi.crm.api.service.UserPermissionService;
import cn.com.conversant.weizi.crm.api.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by conversant on 2017/1/4.
 */
@Service("metadataService")
public class MetadataServiceImpl implements MetadataService {
    @Resource
    private ProjectMetadataDao projectMetadataDao;
    @Resource
    private ProjectContentDao projectContentDao;
    @Resource
    private ProjectSearchConditionDao projectSearchConditionDao;
    @Resource
    private UserPermissionService userPermissionService;
    @Resource
    private UserService userService;

    public ProjectMetadata getMetadataById(long id) {
        return projectMetadataDao.getMetadataById(id);
    }

    @Transactional
    public ProjectMetadata getMetadataByContentId(long contentId) {
        ProjectContent content=projectContentDao.getContentById(contentId);
        if(content==null){
            return null;
        }
        ProjectMetadata metadata=projectMetadataDao.getMetadataById(content.getProject_metadata_id());
        return metadata;
    }

    @Transactional
    public void updateMetadataName(long id, String new_name) {
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("metadata_id",id);
        map.put("new_name",new_name);
        projectMetadataDao.updateMetadataName(map);
    }

    @Transactional
    public void deleteMetadataById(long metadata_id) {
        projectContentDao.deleteContentByMetadataId((int)metadata_id);
        projectSearchConditionDao.deleteByMetadataId(metadata_id);
        userPermissionService.deleteByMetadataId(metadata_id);
        projectMetadataDao.deleteMetadataById(metadata_id);
    }

    @Transactional
    public List<ProjectMetadata> getListMetadataByUserId(long userId) {

        //返回一個用戶下面所有的項目内容
        User user=userService.getUserById(userId);
        if (user==null){
            return null;
        }
        List<ProjectMetadata> projectMetadataList=new ArrayList<ProjectMetadata>();
        List<UserPermission> userPermissionList=null;
        if(user.getUserType()!= UserType.ADMINISTRATOR.getCode()){
            userPermissionList=userPermissionService.getListByUserId(userId);
            if(userPermissionList==null){
                return null;
            }

        }else{
            List<ProjectMetadata>  temp=projectMetadataDao.getMetadataListAll();
            if(temp!=null){
                projectMetadataList.addAll(temp);
                Collections.sort(projectMetadataList);
                return projectMetadataList;
            }
        }


        for(int i=0;i<userPermissionList.size();i++){
            UserPermission item=userPermissionList.get(i);
            ProjectMetadata temp=projectMetadataDao.getMetadataById(item.getProject_metadata_id());
            if(temp!=null){
                projectMetadataList.add(temp);
            }

        }
        Collections.sort(projectMetadataList);
        return projectMetadataList;
    }

    @Transactional
    public long addMetadata(long user_id,ProjectMetadata metadata) {

        if(metadata == null){
            return 0;
        }

//        int count=projectMetadataDao.getCountByAllTitleAndName(metadata);
//        if(count > 0){
//            return 0;
//        }

        projectMetadataDao.addMetadata(metadata);
        long metadata_id=projectMetadataDao.getMetadataByAllTitleAndName(metadata).getId();
        UserPermission userPermission=new UserPermission();
        userPermission.setProject_metadata_id(metadata_id);
        userPermission.setUser_id(user_id);
        userPermissionService.addUserPermission(userPermission);
        return metadata_id;
    }
    @Transactional
    public int getCountByAllTitleName(ProjectMetadata metadata) {
        if(metadata == null){
            return 0;
        }
        int count=projectMetadataDao.getCountByAllTitleAndName(metadata);
        return count;
    }
    @Transactional
    public ProjectMetadata getMetadataByAllTitleName(ProjectMetadata metadata) {
        if(metadata == null){
            return null;
        }
        ProjectMetadata result=projectMetadataDao.getMetadataByAllTitleAndName(metadata);
        return result;
    }
}
