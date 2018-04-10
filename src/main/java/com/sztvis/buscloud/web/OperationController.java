package com.sztvis.buscloud.web;

import com.sztvis.buscloud.model.domain.TramDriverInfo;
import com.sztvis.buscloud.model.domain.TramLineInfo;
import com.sztvis.buscloud.model.dto.BusAndDeviceViewModel;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IDriverService;
import com.sztvis.buscloud.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/18 下午1:55
 */
@Controller
@RequestMapping("/operation")
public class OperationController  extends BaseController{

    @Autowired
    private ILineService iLineService;
    @Autowired
    private IDeviceService iDeviceService;
    @Autowired
    private IDriverService iDriverService;

    @RequestMapping("/line")
    public ModelAndView lineView(){
        ModelAndView modelAndView = new ModelAndView("/operation/line");
        return modelAndView;
    }

    @RequestMapping("/linefrom")
    public ModelAndView lineFrom(long id){
        ModelAndView modelAndView = new ModelAndView("/operation/linefrom");
        TramLineInfo lineInfo = new TramLineInfo();
        if(id != 0){
            lineInfo = this.iLineService.getLineInfo(id);
        }
        modelAndView.getModel().put("obj",lineInfo);
        return modelAndView;
    }

    @RequestMapping("/bus")
    public ModelAndView bus(){
        ModelAndView modelAndView = new ModelAndView("/operation/bus");
        return modelAndView;
    }

    @RequestMapping("/busfrom")
    public ModelAndView getDeviceInfo(long id){
        ModelAndView modelAndView = new ModelAndView("/operation/busfrom");
        BusAndDeviceViewModel model = new BusAndDeviceViewModel();
        if(id != 0)
            model = this.iDeviceService.getDeviceViewModel(id);
        modelAndView.getModel().put("obj",model);
        return modelAndView;
    }

    @RequestMapping("/alarm")
    public ModelAndView alarm(){
        ModelAndView modelAndView = new ModelAndView("/operation/basic");
        return  modelAndView;
    }

    @RequestMapping("/driver")
    public ModelAndView driver(){
        ModelAndView modelAndView = new ModelAndView("/operation/driver");
        return modelAndView;
    }
    @RequestMapping("/driverfrom")
    public ModelAndView driverfrom(long id){
        ModelAndView modelAndView = new ModelAndView("/operation/driverfrom");
        TramDriverInfo driverInfo = new TramDriverInfo();
        if(id != 0){
            driverInfo = this.iDriverService.getDriverInfo(id);
        }
        modelAndView.getModel().put("obj",driverInfo);
        return modelAndView;
    }


}
