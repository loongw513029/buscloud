package com.sztvis.buscloud.web;

import com.sztvis.buscloud.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController{

    @Autowired
    private IMenuService iMenuService;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.getModel().put("navs",iMenuService.GetExtNavDataMenu(0L));
        return modelAndView;
    }

    @RequestMapping("/welcome")
    public ModelAndView welcome(){
        ModelAndView model = new ModelAndView("welcome");
        return model;
    }

}
