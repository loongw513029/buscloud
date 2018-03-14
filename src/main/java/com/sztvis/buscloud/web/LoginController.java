package com.sztvis.buscloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController{

    @RequestMapping("")
    public ModelAndView Login(){
        ModelAndView model = new ModelAndView("login");
        return model;
    }

}
