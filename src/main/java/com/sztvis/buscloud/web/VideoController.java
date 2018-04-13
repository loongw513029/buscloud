package com.sztvis.buscloud.web;

import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/video")
public class VideoController extends BaseController {
    @Autowired
    private IDeviceService iDeviceService;

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

    @RequestMapping("/ocx")
    public ModelAndView ocx(String serverip,int type,String code,int channel,String clientip){
        ModelAndView modelAndView = new ModelAndView("/video/ocx");
        modelAndView.getModel().put("serverip",serverip);
        modelAndView.getModel().put("type",type);
        modelAndView.getModel().put("code",code);
        modelAndView.getModel().put("channel",channel);
        modelAndView.getModel().put("clientip",clientip);
        return modelAndView;
    }
}
