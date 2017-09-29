package cn.com.conversant.weizi.crm.api.controller;

import cn.com.conversant.commons.paging.PagingList;
import cn.com.conversant.weizi.crm.api.constans.APIRequestUrl;
import cn.com.conversant.weizi.crm.api.constans.AppealStatus;
import cn.com.conversant.weizi.crm.api.dto.UserAppealJson;
import cn.com.conversant.weizi.crm.api.entity.User;
import cn.com.conversant.weizi.crm.api.entity.UserAppeal;
import cn.com.conversant.weizi.crm.api.mvc.UserRequired;
import cn.com.conversant.weizi.crm.api.service.UserAppealService;
import cn.com.conversant.weizi.crm.api.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conversant on 2017/1/11.
 */
@Controller
public class ProjectAppealController {

    @Autowired
    private UserAppealService appealService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {APIRequestUrl.APPEAL_MANAGE}, method = RequestMethod.GET)
    public ModelAndView appealManage(HttpServletRequest request,
                                     @PathVariable("ctx") String ctx){
        ModelAndView modelAndView = new ModelAndView("/appeal");
        modelAndView.addObject("ctx",ctx);
        return modelAndView;
    }

    @RequestMapping(value = {APIRequestUrl.APPEAL_LIST}, method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public PagingList<UserAppeal> appealList(@UserRequired User admin,
                                           @RequestParam(value = "p_number", required = false, defaultValue = "1") int pageNumber,
                                           @RequestParam(value = "p_size", required = false, defaultValue = "20") int pageSize,
                                           @RequestParam(value = "sSearch", required = false, defaultValue = "") String searchItem,
                                           @RequestParam(value = "projectFilter", required = false, defaultValue = "0") long projectId) {
        PagingList<UserAppeal> appealPagingList = null;

        if ((!StringUtils.isBlank(searchItem))||projectId>0){
            appealPagingList = appealService.searchAppeal(searchItem, projectId, pageNumber, pageSize);
        }else{
            appealPagingList = appealService.listAppeal(pageNumber, pageSize);
        }

        return appealPagingList;
    }

    @RequestMapping(value = {APIRequestUrl.APPEAL_EDIT}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void appealEdit(
            @UserRequired User admin,
            @RequestParam(value = "appeal_id", required = true) long appealId,
            @RequestParam(value = "status", required = true) int status) {
        appealService.changeStatus(admin.getId(), appealId, status);
    }

    @RequestMapping(value = {APIRequestUrl.APPEAL_STORE_ALL}, method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<Object, Object> appealGetByStore(HttpServletRequest request,
                                                @UserRequired User admin,
                                                @PathVariable(value = "store_id") String store_id) {

        List<UserAppeal> userAppealList = appealService.appealGetByStore(store_id);
        if(userAppealList==null){
            return null;
        }
        List<UserAppealJson> userAppealJsonList=new ArrayList<UserAppealJson>();
        for(int i=0;i<userAppealList.size();i++){
            UserAppeal userAppeal=userAppealList.get(i);
            UserAppealJson item=new UserAppealJson();
            item.setId(userAppeal.getId());
            item.setContent(userAppeal.getContent());
            item.setStore_code(userAppeal.getStore_code());
            item.setCreated((new SimpleDateFormat("yyyy-MM-dd")).format(userAppeal.getCreated()));
            if(userAppeal.getStatus()== AppealStatus.WAITING.getCode()){
                item.setStatus("等待处理");
            }else if(userAppeal.getStatus()==AppealStatus.DONE.getCode()){
                item.setStatus("已经处理");
            }else if(userAppeal.getStatus()==AppealStatus.NODATA.getCode()){
                item.setStatus("申诉资料不全，不予处理");
            }else if (userAppeal.getStatus()==AppealStatus.HASDATA.getCode()){
                item.setStatus("数据无误，无需更改");
            }
            User user=userService.getUserById(userAppeal.getAuthor());
            item.setAuthor(user.getUsername());
            setImages(item, userAppeal);

            userAppealJsonList.add(item);
        }

        Map<Object, Object> jsonmap = new HashMap<Object, Object>();
        jsonmap.put("data", userAppealJsonList);

        return jsonmap;
    }

    private static void setImages(UserAppealJson userAppealJson, UserAppeal userAppeal){
        if(userAppeal.getImage1()!=null){
            userAppealJson.getImages().add(userAppeal.getImage1());
        }else {
            return;
        }
        if(userAppeal.getImage2()!=null){
            userAppealJson.getImages().add(userAppeal.getImage2());
        }else {
            return;
        }
        if(userAppeal.getImage3()!=null){
            userAppealJson.getImages().add(userAppeal.getImage3());
        }else {
            return;
        }
        if(userAppeal.getImage4()!=null){
            userAppealJson.getImages().add(userAppeal.getImage4());
        }else {
            return;
        }
        if(userAppeal.getImage5()!=null){
            userAppealJson.getImages().add(userAppeal.getImage5());
        }else {
            return;
        }
        if(userAppeal.getImage6()!=null){
            userAppealJson.getImages().add(userAppeal.getImage6());
        }else {
            return;
        }
        if(userAppeal.getImage7()!=null){
            userAppealJson.getImages().add(userAppeal.getImage7());
        }else {
            return;
        }
        if(userAppeal.getImage8()!=null){
            userAppealJson.getImages().add(userAppeal.getImage8());
        }else {
            return;
        }
        if(userAppeal.getImage9()!=null){
            userAppealJson.getImages().add(userAppeal.getImage9());
        }else {
            return;
        }
        if(userAppeal.getImage10()!=null){
            userAppealJson.getImages().add(userAppeal.getImage10());
        }else {
            return;
        }

    }
}


