package cn.com.conversant.weizi.crm.api.dao;

import cn.com.conversant.weizi.crm.api.entity.ProjectContent;
import cn.com.conversant.weizi.crm.api.entity.ProjectMetadata;
import cn.com.conversant.weizi.crm.commons.annotation.DataAccessRepository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by conversant on 2017/1/4.
 */
@DataAccessRepository
public interface ProjectMetadataDao {
    void deleteMetadataById(long id);
    void updateMetadataName(HashMap map);
    ProjectMetadata getMetadataById(long id);
    List<ProjectMetadata> getMetadataListAll();
    int getCountByAllTitleAndName(ProjectMetadata item);
    ProjectMetadata getMetadataByAllTitleAndName(ProjectMetadata item);
    void addMetadata(ProjectMetadata item);
    void updateById(ProjectMetadata item);
}
