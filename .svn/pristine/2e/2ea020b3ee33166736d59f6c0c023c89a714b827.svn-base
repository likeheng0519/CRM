package cn.com.conversant.weizi.crm.api.controller;

import cn.com.conversant.weizi.crm.api.constans.APIRequestUrl;
import cn.com.conversant.weizi.crm.api.dto.FileInfom;
import cn.com.conversant.weizi.crm.api.dto.FtpStoreFieldsJson;
import cn.com.conversant.weizi.crm.api.entity.ProjectContent;
import cn.com.conversant.weizi.crm.api.entity.ProjectMetadata;
import cn.com.conversant.weizi.crm.api.entity.User;
import cn.com.conversant.weizi.crm.api.entity.UserPermission;
import cn.com.conversant.weizi.crm.api.mvc.UserRequired;
import cn.com.conversant.weizi.crm.api.service.ContentService;
import cn.com.conversant.weizi.crm.api.service.MetadataService;
import cn.com.conversant.weizi.crm.api.service.SearchConditionService;
import cn.com.conversant.weizi.crm.api.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by conversant on 2017/1/5.
 */
@Controller
public class MyProjectController {
    @Resource
    private UserService userService;
    @Resource
    private ContentService contentService;
    @Resource
    private MetadataService metadataService;
    @Resource
    private SearchConditionService searchConditionService;

    private Logger logger = Logger.getLogger(MyProjectController.class);

    @RequestMapping(value = {APIRequestUrl.PROJECT_HOME}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,
                              @UserRequired User admin,
                              @PathVariable("ctx") String ctx){
        ModelAndView modelAndView = new ModelAndView("/project");
        modelAndView.addObject("ctx",ctx);
        modelAndView.addObject("user_id",admin.getId());
        modelAndView.addObject("user_name",admin.getUsername());
        modelAndView.addObject("user_type",admin.getUserType());
        return modelAndView;
    }

    @RequestMapping(value = {APIRequestUrl.PROJECT_CONTENT_DETAIL}, method = RequestMethod.GET)
    public ModelAndView projectContentDetail(HttpServletRequest request,
                                             @PathVariable("ctx") String ctx,
                                             @PathVariable(value = "content_id") String content_id,
                                             @RequestParam(value = "metadata_id") String metadata_id,
                                             @UserRequired User admin){

        ModelAndView modelAndView = new ModelAndView("/project-detail");
        modelAndView.addObject("content_id",content_id);
        ProjectContent projectContent=contentService.getProjectContentById(new Long(content_id));
        String store_id=projectContent.getF3();
        List<FileInfom>  fileInfoms5=new ArrayList<FileInfom>();
        List<FileInfom>  fileInfoms6=new ArrayList<FileInfom>();
        try {
            String classpath = request.getSession().getServletContext().getRealPath("");
            String storePath=classpath+"/ftp/"+metadata_id+"/"+store_id;
            File file = new File(storePath);
            if(file.exists() && file .isDirectory()){

                String field5=storePath+"/"+"5";
                File file_field5=new File(field5);
                if(file_field5.exists() && file_field5 .isDirectory()){
                    fileInfoms5=getFileName("作者",metadata_id,store_id,"5",file_field5);
                }
                String field6=storePath+"/"+"6";
                File file_field6=new File(field6);
                if(file_field6.exists() && file_field6 .isDirectory()){
                    fileInfoms6=getFileName("作者",metadata_id,store_id,"6",file_field6);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(APIRequestUrl.PROJECT_FTP_STORE+" get ftp by store id about image and cocntent error."+":"+e.getMessage());
            return null;
        }

        if(fileInfoms5!=null&&fileInfoms5.size()>0){
            modelAndView.addObject("list_field5", fileInfoms5);
        }
        if(fileInfoms6!=null&&fileInfoms6.size()>0){
            modelAndView.addObject("list_field6", fileInfoms6);
        }

        modelAndView.addObject("ctx",ctx);
        return modelAndView;
    }

    @RequestMapping(value = {APIRequestUrl.PROJECT_MANAGE}, method = RequestMethod.GET)
    public ModelAndView projectManage(HttpServletRequest request,
                                      @UserRequired User admin,
                                      @PathVariable("ctx") String ctx){
        ModelAndView modelAndView = new ModelAndView("/project-manage");
        List<ProjectMetadata> projectMetadataList = metadataService.getListMetadataByUserId(admin.getId());
        if(projectMetadataList!=null){
            modelAndView.addObject("projectMetadataList",projectMetadataList);
        }
        modelAndView.addObject("ctx",ctx);
        return modelAndView;
    }

    private static List<FileInfom> getFileName(String author,String metadata_id,String store,String number,File file){
        String path = file.getPath();
        List<FileInfom>  fileInfomList=new ArrayList<FileInfom>();
        String [] filelist = file.list();
        for (int i = 0; i < filelist.length; i++) {
            File readfile = new File(path + "/" + filelist[i]);
            if (!readfile.isDirectory()) {
                FileInfom item=new FileInfom();
                item.setFile_name(filelist[i]);
                item.setFile_path("/ftp/"+metadata_id+"/"+store+"/"+number+"/"+filelist[i]);
                item.setFile_author(author);
                item.setFile_size(String.valueOf(readfile.length()/1048576));
                item.setFile_time(new Timestamp(readfile.lastModified()).toString());
                fileInfomList.add(item);
            }
        }
        return fileInfomList;
    }

}
