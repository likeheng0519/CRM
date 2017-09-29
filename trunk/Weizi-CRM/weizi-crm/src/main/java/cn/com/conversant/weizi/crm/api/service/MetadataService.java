package cn.com.conversant.weizi.crm.api.service;

import cn.com.conversant.weizi.crm.api.entity.ProjectContent;
import cn.com.conversant.weizi.crm.api.entity.ProjectMetadata;

import java.util.List;

/**
 * Created by conversant on 2017/1/4.
 */
public interface MetadataService {
    public ProjectMetadata getMetadataById(long id);
    public void deleteMetadataById(long metadata_id);
    public void updateMetadataName(long id, String new_name);
    public List<ProjectMetadata> getListMetadataByUserId(long userId);
    public ProjectMetadata getMetadataByContentId(long contentId);
    public long addMetadata(long user_id,ProjectMetadata metadata);
    public int getCountByAllTitleName(ProjectMetadata metadata);
    public ProjectMetadata getMetadataByAllTitleName(ProjectMetadata metadata);

}
