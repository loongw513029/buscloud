package com.sztvis.buscloud.web;

import com.sztvis.buscloud.model.dto.LoginParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    @RequestMapping("")
    public ModelAndView Login(){
        ModelAndView model = new ModelAndView("login");

        return model;
    }
}
