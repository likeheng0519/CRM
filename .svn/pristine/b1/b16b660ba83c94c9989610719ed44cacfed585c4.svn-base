package cn.com.conversant.weizi.crm.api.controller;

/**
 * Created by jianggangli on 2016/10/15.
 */

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.conversant.commons.paging.PagingList;
import cn.com.conversant.weizi.crm.api.constans.APIRequestUrl;
import cn.com.conversant.weizi.crm.api.entity.User;
import cn.com.conversant.weizi.crm.api.mvc.UserRequired;
import cn.com.conversant.weizi.crm.api.service.UserService;
import cn.com.conversant.weizi.crm.api.service.impl.UserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    private Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value = APIRequestUrl.USER_LOGIN, method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userLogin(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam(value = "ctx") String ctx){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = Boolean.parseBoolean(request.getParameter("rememberMe"));

        if (logger.isDebugEnabled()) {
            logger.debug("rememberMe==" + rememberMe);
        }

        Map<String, Object> map = new HashMap();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        token.clear();

        User user = (User) subject.getPrincipal();

        /*request.getSession().setAttribute("user_id",String.valueOf(user.getId()));
        request.getSession().setAttribute("user_name",user.getUsername());
        request.getSession().setAttribute("user_type",String.valueOf(user.getUserType()));
        request.getSession().setAttribute("user", user);*/

        map.put("redirecturl", "/"+ctx+"/project/home");
        return map;
    }

    @RequestMapping(value = APIRequestUrl.USER_LOGOUT, method= RequestMethod.POST)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            try {
                User user = (User) subject.getPrincipal();
                if (user != null) {
                    subject.logout();   // removes all identifying information and invalidates their session too.
                }
            } catch (Exception ex) {
                logger.debug("logout exception ignored.", ex);
            }
        }
    }

    @RequestMapping(value = {APIRequestUrl.USER_MANAGE}, method = RequestMethod.GET)
    public ModelAndView userManage(@UserRequired User admin,
                                   @PathVariable("ctx") String ctx){
        ModelAndView modelAndView = new ModelAndView("/user-manage");
        modelAndView.addObject("ctx",ctx);
        return modelAndView;
    }

    @RequestMapping(value = {APIRequestUrl.USER_ADD}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void userAdd(
            @UserRequired User admin,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "user_type", required = true) int userType,
            @RequestParam(value = "password", required = true) String password) {
        User user = new User();
        user.setUsername(username);
        user.setUserType(userType);
        user.setPassword(password);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        userService.addUser(user);
    }

    @RequestMapping(value = {APIRequestUrl.USER_EDIT}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void userEdit(
            @UserRequired User admin,
            @RequestParam(value = "user_id", required = true) long userId,
            @RequestParam(value = "user_type", required = true) int userType,
            @RequestParam(value = "old_password", required = true) String oldPassword,
            @RequestParam(value = "new_password", required = true) String newPassword) {
        userService.editUser(userId, userType, oldPassword, newPassword);
    }

    @RequestMapping(value = {APIRequestUrl.USER_DELETE}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void userDelete(
            @UserRequired User admin,
            @RequestParam(value = "user_id", required = true) long userId) {
        userService.deleteUser(userId);
    }

    @RequestMapping(value = {APIRequestUrl.USER_LIST}, method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public PagingList<User> userList(@UserRequired User admin,
                                                @RequestParam(value = "p_field", required = false, defaultValue = "created_time") String sortColumn,
                                                @RequestParam(value = "p_type", required = false, defaultValue = "desc") String sortType,
                                                @RequestParam(value = "p_number", required = false, defaultValue = "1") int pageNumber,
                                                @RequestParam(value = "p_size", required = false, defaultValue = "20") int pageSize,
                                                @RequestParam(value = "sSearch", required = false, defaultValue = "") String searchItem) {
        PagingList<User> userPagingList = null;

        if ((!StringUtils.isBlank(searchItem))){
            userPagingList = userService.searchUser(searchItem, pageNumber, pageSize);
        } else {
            userPagingList = userService.listUser(pageNumber, pageSize);
        }

        return userPagingList;
    }
}

