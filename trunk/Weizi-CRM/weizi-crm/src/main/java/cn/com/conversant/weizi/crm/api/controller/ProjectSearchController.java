package cn.com.conversant.weizi.crm.api.controller;

import cn.com.conversant.weizi.crm.api.constans.APIRequestUrl;
import cn.com.conversant.weizi.crm.api.constans.UserType;
import cn.com.conversant.weizi.crm.api.entity.ProjectSearchCondition;
import cn.com.conversant.weizi.crm.api.entity.User;
import cn.com.conversant.weizi.crm.api.entity.UserPermission;
import cn.com.conversant.weizi.crm.api.mvc.UserRequired;
import cn.com.conversant.weizi.crm.api.service.*;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conversant on 2017/1/4.
 */
@Controller
public class ProjectSearchController {
    @Resource
    private UserService userService;
    @Resource
    private ContentService contentService;
    @Resource
    private MetadataService metadataService;
    @Resource
    private SearchConditionService searchConditionService;
    @Resource
    private UserPermissionService userPermissionService;

    private Logger logger = Logger.getLogger(ProjectSearchController.class);

    @RequestMapping(value = {APIRequestUrl.PROJECT_SEARCH_CONDITION}, method = RequestMethod.GET, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public ProjectSearchCondition getSearchConditionByMetadataId(HttpServletRequest request,
                                                                 @UserRequired User admin,
                                                                 @PathVariable(value = "metadata_id") String metadata_id
                                                                 ){

        if(metadata_id==null||new Long(metadata_id)==0){
            return null;
        }

        ProjectSearchCondition searchCondition=null;
        //項目對應的搜索框及搜索框對應的内容
        ProjectSearchCondition inputConditon=searchConditionService.getProjectSearchConditionByMetaId(new Long(metadata_id));
        if(inputConditon==null) {
            logger.error(APIRequestUrl.PROJECT_SEARCH_CONDITION+"get the project search conditon data error,when metadata id = "+metadata_id);
            return null;
        }
        searchCondition = contentService.getDistinctContentBySearchCondition(inputConditon);

        return searchCondition;
    }


    @RequestMapping(value = {APIRequestUrl.PROJECT_SEARCH_UPDATE_INSERT_CONDITION}, method = RequestMethod.GET, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public Map<String, String> updateinsertProjectSearchCondition(HttpServletRequest request,
                                                                  @UserRequired User admin,
                                                                  @RequestParam(value = "metadata_id") String metadata_id,
                                                                  @RequestParam(value = "field1", required = false) String field1,
                                                                  @RequestParam(value = "field1_type", required = false) String field1_type,
                                                                  @RequestParam(value = "field1_title", required = false) String field1_title,
                                                                  @RequestParam(value = "field2", required = false) String field2,
                                                                  @RequestParam(value = "field2_type", required = false) String field2_type,
                                                                  @RequestParam(value = "field2_title", required = false) String field2_title,
                                                                  @RequestParam(value = "field3", required = false) String field3,
                                                                  @RequestParam(value = "field3_type", required = false) String field3_type,
                                                                  @RequestParam(value = "field3_title", required = false) String field3_title,
                                                                  @RequestParam(value = "field4", required = false) String field4,
                                                                  @RequestParam(value = "field4_type", required = false) String field4_type,
                                                                  @RequestParam(value = "field4_title", required = false) String field4_title,
                                                                  @RequestParam(value = "field5", required = false) String field5,
                                                                  @RequestParam(value = "field5_type", required = false) String field5_type,
                                                                  @RequestParam(value = "field5_title", required = false) String field5_title
    ){
        HashMap retmap = new HashMap<String, String>();

        if(new Long(metadata_id)==0){
            retmap.put("status", "failed");
            return retmap;
        }

        ProjectSearchCondition searchCondition = new ProjectSearchCondition();
        searchCondition.setProject_metadata_id(new Long(metadata_id));
        if(field1!=null&&field1_type!=null&&field1_title!=null){
            searchCondition.setField1(field1);
            searchCondition.setField1_type(new Long(field1_type).intValue());
            searchCondition.setField1_title(field1_title);

        }
        if(field2!=null&&field2_type!=null&&field2_title!=null){
            searchCondition.setField2(field2);
            searchCondition.setField2_type(new Long(field2_type).intValue());
            searchCondition.setField2_title(field2_title);

        }
        if(field3!=null&&field3_type!=null&&field3_title!=null){
            searchCondition.setField3(field3);
            searchCondition.setField3_type(new Long(field3_type).intValue());
            searchCondition.setField3_title(field3_title);

        }
        if(field4!=null&&field4_type!=null&&field4_title!=null){
            searchCondition.setField4(field4);
            searchCondition.setField4_type(new Long(field4_type).intValue());
            searchCondition.setField4_title(field4_title);

        }
        if(field5!=null&&field5_type!=null&&field5_title!=null){
            searchCondition.setField5(field5);
            searchCondition.setField5_type(new Long(field5_type).intValue());
            searchCondition.setField5_title(field5_title);

        }

        try {
            searchConditionService.updateisnertProjectSearchCondition(searchCondition);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(APIRequestUrl.PROJECT_SEARCH_UPDATE_INSERT_CONDITION + "update or insert project search condition data error[" + metadata_id + "]:" + e.getMessage());
            retmap.put("status", "failed");
            return retmap;
        }
        retmap.put("status", "success");

        return retmap;
    }

}
