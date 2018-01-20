package com.sztvis.buscloud.web;

import com.sztvis.buscloud.model.domain.TramLineInfo;
import com.sztvis.buscloud.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
