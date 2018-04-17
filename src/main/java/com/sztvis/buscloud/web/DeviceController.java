package com.sztvis.buscloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/31 下午4:58
 */
@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController {

    @RequestMapping("/inspect")
    public ModelAndView Inspect(){
        ModelAndView modelAndView = new ModelAndView("/device/inspect");
        return modelAndView;
    }

    @RequestMapping("/set")
    public ModelAndView DeviceSet(int id){
        ModelAndView modelAndView = new ModelAndView("/device/set");
        return modelAndView;
    }

    @RequestMapping("/maintain")
    public ModelAndView DeviceSet(){
        ModelAndView modelAndView = new ModelAndView("/device/maintain");
        return modelAndView;
    }
}
