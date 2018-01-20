package com.sztvis.buscloud.web;

import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/8 上午11:23
 */
@Controller
@RequestMapping("/can")
public class CanController {

    @RequestMapping("/preview")
    public ModelAndView Preview(){
        ModelAndView model =new ModelAndView("/can/preview");
        return model;
    }

    @RequestMapping("/history")
    public ModelAndView list(){
        ModelAndView model = new ModelAndView("/can/history");
        return model;
    }
}
