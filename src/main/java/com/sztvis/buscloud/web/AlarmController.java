package com.sztvis.buscloud.web;

import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.dto.AlarmViewModel;
import com.sztvis.buscloud.service.IAlarmService;
import com.sztvis.buscloud.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/23 下午2:52
 */
@Controller
@RequestMapping("/alarm")
public class AlarmController {
    @Autowired
    private IAlarmService iAlarmService;
    @Autowired
    private IDeviceService iDeviceService;
    @RequestMapping("/list")
    public ModelAndView List(){
        ModelAndView modelAndView = new ModelAndView("/alarm/list");
        return modelAndView;
    }

    @RequestMapping("/view")
    public ModelAndView view(long id){
        AlarmViewModel alarmViewModel = new AlarmViewModel();
        if(id !=0 )
            alarmViewModel = this.iAlarmService.getAlarmViewModel(id);
        ModelAndView modelAndView = new ModelAndView("/alarm/view");
        modelAndView.getModel().put("obj",alarmViewModel);
        modelAndView.getModel().put("length",alarmViewModel.getValue().split(",").length);
        return modelAndView;
    }

    @RequestMapping("/video")
    public ModelAndView video(String code) {
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(code);
        ModelAndView modelAndView = new ModelAndView("/alarm/video");
        modelAndView.getModel().put("obj", deviceInfo);
        return modelAndView;
    }

    @RequestMapping("/statics")
    public ModelAndView statics(){
        ModelAndView modelAndView = new ModelAndView("/alarm/statics");
        return modelAndView;
    }

}
