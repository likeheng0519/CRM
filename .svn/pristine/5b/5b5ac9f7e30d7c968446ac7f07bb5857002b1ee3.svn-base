package cn.com.conversant.weizi.crm.api.controller;

import cn.com.conversant.weizi.crm.api.constans.APIRequestUrl;
import cn.com.conversant.weizi.crm.api.constans.HasPermission;
import cn.com.conversant.weizi.crm.api.constans.UserType;
import cn.com.conversant.weizi.crm.api.dto.UserPermissionJson;
import cn.com.conversant.weizi.crm.api.entity.User;
import cn.com.conversant.weizi.crm.api.entity.UserPermission;
import cn.com.conversant.weizi.crm.api.mvc.UserRequired;
import cn.com.conversant.weizi.crm.api.service.*;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conversant on 2017/1/11.
 */
@Controller
public class UserPermissionController {
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

    private Logger logger = Logger.getLogger(ProjectContentController.class);

    @RequestMapping(value = APIRequestUrl.PROJECT_CUSTOMER_PERMISSION_ALL, method = RequestMethod.POST, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public List<UserPermissionJson> getAllUserPermission(HttpServletRequest request,
                                                         @UserRequired User admin,
                                                         @RequestParam(value = "metadata_id", required = true)String metadata_id) {
        long metadataId=new Long(metadata_id);
        List<UserPermissionJson> list=new ArrayList<UserPermissionJson>();
        List<UserPermission> userPermissionList=userPermissionService.getAllCustomerPermissionList();

        List<User> userList=userService.getUserByNotType(UserType.ADMINISTRATOR.getCode());

        if(userList==null){
            return  null;
        }
        for(int i=0;i<userList.size();i++){
            User user=userList.get(i);
            UserPermissionJson userPermissionJson=new UserPermissionJson();
            userPermissionJson.setUser_id(user.getId());
            userPermissionJson.setUser_name(user.getUsername());
            userPermissionJson.setUser_type(UserType.CUSTOMER.getCode());
            userPermissionJson.setHasPermission(HasPermission.NoPermission.getCode());
            if(userPermissionList==null){
                continue;
            }
            for(int j=0;j<userPermissionList.size();j++){
                UserPermission userPermission=userPermissionList.get(j);
                if(user.getId()==userPermission.getUser_id()&&metadataId==userPermission.getProject_metadata_id()){
                    userPermissionJson.setMetadata_id(metadataId);
                    userPermissionJson.setUser_permission_id(userPermission.getId());
                    userPermissionJson.setHasPermission(HasPermission.YesPermission.getCode());
                }
            }
            list.add(userPermissionJson);
        }

        return  list;
    }


    @RequestMapping(value = APIRequestUrl.PROJECT_CUSTOMER_PERMISSION_UPDATE, method = RequestMethod.POST, produces = APIRequestUrl.CONTENT_TYPE_JSON)
    @ResponseBody
    public Map<String,String> getUpdateUserPermission(HttpServletRequest request,
                                                      @UserRequired User admin,
                                                      @RequestParam(value = "permissions", required = true)  String customer_permissions) {
        HashMap retmap = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, UserPermissionJson.class);
        List<UserPermissionJson> list = null;
        try {
            list = objectMapper.readValue(customer_permissions, javaType);
            userPermissionService.updateCustomerPermissionList(list);

        }catch (IOException ioe){
            ioe.printStackTrace();
            logger.error(APIRequestUrl.PROJECT_CUSTOMER_PERMISSION_UPDATE+" update user permission error.");
        }
        retmap.put("status", "success");
        return retmap;
    }

}
