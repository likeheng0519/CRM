package cn.com.conversant.weizi.crm.api.service;

import cn.com.conversant.weizi.crm.api.entity.ProjectContent;
import cn.com.conversant.weizi.crm.api.entity.ProjectSearchCondition;

import java.util.List;

/**
 * Created by conversant on 2017/1/4.
 */
public interface ContentService {

    public int addContentList(List<ProjectContent> contentList);
    public void deleteContentByContentId(long id);
    public void updateContentByContentProjectContent(ProjectContent item);
    public void deleteContentByMetadataId(int metadataId);
    public void updateContentList(int metadataId,List<ProjectContent> contentList);
    public List<ProjectContent> getListByMetadataId(long metadataId);
    public ProjectContent getProjectContentById(long id);
    public List<ProjectContent> getListBySearchCondition(ProjectSearchCondition searchCondition);
    public ProjectSearchCondition getDistinctContentBySearchCondition(ProjectSearchCondition searchCondition);
}
