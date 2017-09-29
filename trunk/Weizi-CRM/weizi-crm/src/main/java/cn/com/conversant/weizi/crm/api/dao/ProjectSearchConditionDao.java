package cn.com.conversant.weizi.crm.api.dao;

import cn.com.conversant.weizi.crm.api.entity.ProjectMetadata;
import cn.com.conversant.weizi.crm.api.entity.ProjectSearchCondition;
import cn.com.conversant.weizi.crm.commons.annotation.DataAccessRepository;

/**
 * Created by conversant on 2017/1/4.
 */
@DataAccessRepository
public interface ProjectSearchConditionDao {
    ProjectSearchCondition getProjectSearchConditionByMetaId(long metadataId);
    int getCountByMetadataId(long metadataId);
    void deleteByMetadataId(long metadataId);
    void addSearchCondition(ProjectSearchCondition item);
    void updateBySearchCondition(ProjectSearchCondition item);
}
