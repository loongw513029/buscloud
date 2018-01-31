package com.sztvis.buscloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/24 下午3:45
 */
@Controller
@RequestMapping("/map")
public class MapController {

    @RequestMapping("preview")
    public ModelAndView Preview(){
        ModelAndView modelAndView = new ModelAndView("/map/preview");
        return modelAndView;
    }
}
