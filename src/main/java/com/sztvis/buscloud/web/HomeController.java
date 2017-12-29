package com.sztvis.buscloud.web;

import com.sztvis.buscloud.DbConfigPros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class HomeController extends BaseController{

    @Autowired
    private DbConfigPros dbConfigPros;
    @RequestMapping("")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.getModel().put("url",dbConfigPros.getHost());
        return modelAndView;
    }

}
