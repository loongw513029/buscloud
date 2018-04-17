package com.sztvis.buscloud.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/aitraffic")
public class AitrafficController extends BaseController{

    @RequestMapping("/pay")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("/aitraffic/pay");
        return modelAndView;
    }
}
