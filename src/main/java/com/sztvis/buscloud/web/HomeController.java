package com.sztvis.buscloud.web;

import com.sztvis.buscloud.DbConfigPros;
import com.sztvis.buscloud.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class HomeController extends BaseController{

    @Autowired
    private IMenuService iMenuService;

    @RequestMapping("")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.getModel().put("navs",iMenuService.GetExtNavDataMenu(0L));
        return modelAndView;
    }



}
