package cn.com.conversant.weizi.crm.api.service.impl;

import cn.com.conversant.weizi.crm.api.dao.ProjectSearchConditionDao;
import cn.com.conversant.weizi.crm.api.entity.ProjectSearchCondition;
import cn.com.conversant.weizi.crm.api.service.SearchConditionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by conversant on 2017/1/4.
 */
@Service("searchConditionService")
public class SearchConditionServiceImpl implements SearchConditionService {
    @Resource
    private ProjectSearchConditionDao projectSearchConditionDao;
    @Transactional
    public ProjectSearchCondition getProjectSearchConditionByMetaId(long project_metadata_id) {
        ProjectSearchCondition projectSearchCondition=projectSearchConditionDao.getProjectSearchConditionByMetaId(project_metadata_id);
        return projectSearchCondition;
    }
    @Transactional
    public int updateisnertProjectSearchCondition(ProjectSearchCondition projectSearchCondition) {
        if(projectSearchCondition == null){
            return 0;
        }
        ProjectSearchCondition result=projectSearchConditionDao.getProjectSearchConditionByMetaId(projectSearchCondition.getProject_metadata_id());
        if(result==null){
            projectSearchConditionDao.addSearchCondition(projectSearchCondition);
        }else{
            projectSearchConditionDao.updateBySearchCondition(projectSearchCondition);
        }
        return 0;
    }

}
