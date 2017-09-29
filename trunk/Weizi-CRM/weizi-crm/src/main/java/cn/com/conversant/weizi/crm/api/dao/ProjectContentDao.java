package cn.com.conversant.weizi.crm.api.dao;

import cn.com.conversant.weizi.crm.api.entity.ProjectContent;
import cn.com.conversant.weizi.crm.api.entity.ProjectSearchCondition;
import cn.com.conversant.weizi.crm.commons.annotation.DataAccessRepository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by conversant on 2017/1/4.
 */
@DataAccessRepository
public interface ProjectContentDao {
    List<ProjectContent> getProjectContentListByMetaId(long metadataid);
    List<ProjectContent> getListByProjectContent(ProjectContent item);
    List<ProjectContent> getListByProjectSearchCondition(HashMap map);
    ProjectContent getContentById(long id);
    void addContent(ProjectContent item);
    void deleteContentById(long id);
    void updateByProjectContent(ProjectContent item);
    void deleteContentByMetadataId(int metadataId);
    List<String> getDistinct(HashMap map);
}
