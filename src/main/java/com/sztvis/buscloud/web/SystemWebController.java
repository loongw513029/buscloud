package com.sztvis.buscloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/system")
public class SystemWebController extends BaseController{

    @RequestMapping("/set")
    public ModelAndView bus(){
        ModelAndView modelAndView = new ModelAndView("/system/set");
        return modelAndView;
    }
}
