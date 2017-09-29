package cn.com.conversant.weizi.crm.api.service.impl;

import cn.com.conversant.weizi.crm.api.constans.SearchType;
import cn.com.conversant.weizi.crm.api.dao.ProjectContentDao;
import cn.com.conversant.weizi.crm.api.dao.UserDao;
import cn.com.conversant.weizi.crm.api.entity.ProjectContent;
import cn.com.conversant.weizi.crm.api.entity.ProjectSearchCondition;
import cn.com.conversant.weizi.crm.api.service.ContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by conversant on 2017/1/4.
 */
@Service("contentService")
public class ContentServiceImpl implements ContentService {
    @Resource
    private ProjectContentDao projectContentDao;

    @Transactional
    public int addContentList(List<ProjectContent> contentList) {

        if(contentList == null || contentList.size()==0){
            return 0;
        }

        int count=contentList.size();
        for (int i=0; i<count;i++){
            ProjectContent item = contentList.get(i);
            projectContentDao.addContent(item);
        }

        return count;
    }

    @Transactional
    public void deleteContentByContentId(long id) {
        projectContentDao.deleteContentById(id);
        return;
    }

    @Transactional
    public void deleteContentByMetadataId(int metadataId) {
        projectContentDao.deleteContentByMetadataId(metadataId);
        return ;
    }

    public void updateContentByContentProjectContent(ProjectContent item) {
        projectContentDao.updateByProjectContent(item);
    }

    @Transactional
    public void updateContentList(int metadataId, List<ProjectContent> contentList) {

        if(contentList == null || contentList.size()==0){
            return ;
        }

        projectContentDao.deleteContentByMetadataId(metadataId);

        int count=contentList.size();
        for (int i=0; i<count;i++){
            ProjectContent item = contentList.get(i);
            projectContentDao.addContent(item);
        }

    }

    @Transactional
    public List<ProjectContent> getListBySearchCondition(ProjectSearchCondition searchCondition){
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("metadata_id",searchCondition.getProject_metadata_id());
        if(searchCondition.getField1()!=null){
            map.put("field1", searchCondition.getField1());
            map.put("field1_content", searchCondition.getField1_content());
        }
        if(searchCondition.getField2()!=null){
            map.put("field2", searchCondition.getField2());
            map.put("field2_content", searchCondition.getField2_content());
        }
        if(searchCondition.getField3()!=null){
            map.put("field3", searchCondition.getField3());
            map.put("field3_content", searchCondition.getField3_content());
        }
        if(searchCondition.getField4()!=null){
            map.put("field4", searchCondition.getField4());
            map.put("field4_content", searchCondition.getField4_content());
        }
        if(searchCondition.getField5()!=null){
            map.put("field5", searchCondition.getField5());
            map.put("field5_content", searchCondition.getField5_content());
        }
        if(searchCondition.getSales_manager()!=null){
            map.put("sales_manager", searchCondition.getSales_manager());
        }
        if(searchCondition.getSales_represent()!=null){
            map.put("sales_represent", searchCondition.getSales_represent());
        }

        List<ProjectContent> list=projectContentDao.getListByProjectSearchCondition(map);
        return list;
    }

    @Transactional
    public List<ProjectContent> getListByMetadataId(long metadataId) {
        List<ProjectContent> list=projectContentDao.getProjectContentListByMetaId(metadataId);
        return list;
    }

    @Transactional
    public ProjectContent getProjectContentById(long id) {
        projectContentDao.getContentById(id);
        return projectContentDao.getContentById(id);
    }

    @Transactional
    public ProjectSearchCondition getDistinctContentBySearchCondition(ProjectSearchCondition searchCondition) {
        if(searchCondition.getField1()!=null&&searchCondition.getField1_type()== SearchType.SELECT.getCode()){
            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("metadataId",searchCondition.getProject_metadata_id());
            map.put("column",searchCondition.getField1());
            List<String> list=projectContentDao.getDistinct(map);
            searchCondition.setField1_distinct(list);
        }
        if(searchCondition.getField2()!=null&&searchCondition.getField2_type()== SearchType.SELECT.getCode()){
            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("metadataId",searchCondition.getProject_metadata_id());
            map.put("column",searchCondition.getField2());
            List<String> list=projectContentDao.getDistinct(map);
            searchCondition.setField2_distinct(list);
        }
        if(searchCondition.getField3()!=null&&searchCondition.getField3_type()== SearchType.SELECT.getCode()){
            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("metadataId",searchCondition.getProject_metadata_id());
            map.put("column",searchCondition.getField3());
            List<String> list=projectContentDao.getDistinct(map);
            searchCondition.setField3_distinct(list);
        }
        if(searchCondition.getField4()!=null&&searchCondition.getField4_type()== SearchType.SELECT.getCode()){
            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("metadataId",searchCondition.getProject_metadata_id());
            map.put("column",searchCondition.getField4());
            List<String> list=projectContentDao.getDistinct(map);
            searchCondition.setField4_distinct(list);
        }
        if(searchCondition.getField5()!=null&&searchCondition.getField5_type()== SearchType.SELECT.getCode()){
            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("metadataId",searchCondition.getProject_metadata_id());
            map.put("column",searchCondition.getField5());
            List<String> list=projectContentDao.getDistinct(map);
            searchCondition.setField5_distinct(list);
        }

        return searchCondition;
    }
}
