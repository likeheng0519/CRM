package cn.com.conversant.weizi.crm.api.constans;

/**
 * Created by jianggangli on 2016/10/15.
 */
public class APIRequestUrl {
    //Accept data type
    public static final String  ACCEPT_TYPE_JSON ="application/json; charset=UTF-8";
    public static final String  CONTENT_TYPE_JSON  ="application/json; charset=UTF-8";
    public static final String  CONTENT_TYPE_FORM_URL_ENCODED="application/x-www-form-urlencoded; charset=UTF-8";
    public static final String  CONTENT_TYPE_FORM_DATA="application/form-data; charset=UTF-8";
    //user
    public static final String  USER_REGISTER = "/user/register";
    public static final String  USER_LOGIN = "/user/login";
    public static final String  USER_LOGOUT = "/user/logout";
    public static final String  USER_MANAGE = "{ctx}/user/manage";
    public static final String  USER_ADD = "/user/add";
    public static final String  USER_EDIT = "/user/edit";
    public static final String  USER_DELETE = "/user/delete";
    public static final String  USER_LIST = "/user/list";

    //project home
    public static final String PROJECT_HOME="{ctx}/project/home";
    public static final String PROJECT_CONTENT_DETAIL="{ctx}/project/content/detail/{content_id}";
    public static final String PROJECT_MANAGE="{ctx}/project/manage";

    //project content
    public static final String PROJECT_CONTENT_IMPORT="/content/import";
    public static final String PROJECT_CONTENT_METADATA_ID="/content/metadata/{metadata_id}";
    public static final String PROJECT_CONTENT_ID="/content/id/{id}";
    public static final String PROJECT_DELETE_ID="/content/delete/{id}";
    public static final String PROJECT_UPDATE_ID="/content/update";
    public static final String PROJECT_UPLOAD_IMAGE_APPEAL="/content/appeal/image";
    public static final String PROJECT_UPLOAD_APPEAL="/content/appeal/appeal";
    public static final String PROJECT_FTP_STORE="/content/ftp/store/{store_id}";

    //project metadata
    public static final String PROJECT_METADATA_DELETE="/metadata/delete/{metadata_id}";
    public static final String PROJECT_METADATA_UPDATE_NAME="/metadata/update/name";
    public static final String PROJECT_METADATA_IMPORT="/metadata/import";
    public static final String PROJECT_METADATA_METADATA_ID="/metadata/{metadata_id}";
    public static final String PROJECT_METADATA_NAMES_USER_NAME="/metadata/user";
    public static final String PROJECT_METADATA_CONTENT_ID="/metadata/content";

    //project search condition
    public static final String PROJECT_SEARCH_CONDITION="/search_condition/{metadata_id}";
    public static final String PROJECT_SEARCH_UPDATE_INSERT_CONDITION="/search_condition/updateinsert";

    //project user permission
    public static final String PROJECT_CUSTOMER_PERMISSION_ALL="/userpermission/all";
    public static final String PROJECT_CUSTOMER_PERMISSION_UPDATE="/userpermission/update";

    //appeal
    public static final String  APPEAL_MANAGE = "{ctx}/appeal/manage";
    public static final String  APPEAL_LIST = "/appeal/list";
    public static final String  APPEAL_EDIT = "/appeal/edit";
    public static final String  APPEAL_STORE_ALL = "/appeal/store/{store_id}";

}
