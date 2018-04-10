package com.sztvis.buscloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/video")
public class VideoController extends BaseController {

    @RequestMapping("/preview")
    public ModelAndView preview(){
        ModelAndView modelAndView = new ModelAndView("/video/preview");
        return  modelAndView;
    }

    @RequestMapping("/history")
    public ModelAndView history(){
        ModelAndView modelAndView = new ModelAndView("/video/history");
        return  modelAndView;
    }
}
