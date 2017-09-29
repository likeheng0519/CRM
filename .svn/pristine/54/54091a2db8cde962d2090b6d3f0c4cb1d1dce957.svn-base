package cn.com.conversant.weizi.crm.api.controller;

import cn.com.conversant.weizi.crm.api.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by conversant on 2017/1/5.
 */
@Controller
@Component
public class HomeController {

    private Logger logger = Logger.getLogger(HomeController.class);

    @Value("${project.context.image.dir}")
    private String projectContextImageDir;

    @RequestMapping(value = "/{ctx}/login")
    public ModelAndView login(HttpServletRequest request,
                              @PathVariable("ctx") String ctx) {
        File projectContextImage = new File(projectContextImageDir + ctx + ".png");
        logger.info("loginPage:projectContextImage========"+projectContextImage);
        if (!projectContextImage.exists()) {
            return new ModelAndView("/error/404");
        }
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("ctx",ctx);
        return modelAndView;
    }

    @RequestMapping(value = {"/{ctx}", "/{ctx}/index"})
    public ModelAndView index(HttpServletRequest request,
                              @PathVariable("ctx") String ctx){
        ModelAndView modelAndView = new ModelAndView("redirect:/"+ctx+"/login");
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        if (principals != null && !principals.isEmpty()) {
            User user = (User) subject.getPrincipal();
            if (user != null) {
                modelAndView = new ModelAndView("/project");
                modelAndView.addObject("ctx",ctx);
                modelAndView.addObject("user_id",user.getId());
                modelAndView.addObject("user_name",user.getUsername());
            }
        }
        return modelAndView;
    }
}
