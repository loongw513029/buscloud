package com.sztvis.buscloud.web;

import com.sztvis.buscloud.core.DateStyle;
import com.sztvis.buscloud.core.DateUtil;
import com.sztvis.buscloud.model.dto.HomeAlarmViewModel;
import com.sztvis.buscloud.model.dto.WelcomeModel;
import com.sztvis.buscloud.service.IHomeService;
import com.sztvis.buscloud.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController extends BaseController{

    @Autowired
    private IMenuService iMenuService;
    @Autowired
    private IHomeService iHomeService;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.getModel().put("navs",iMenuService.GetExtNavDataMenu(0L));
        modelAndView.getModel().put("user",getUserInfo());
        return modelAndView;
    }

    @RequestMapping("/welcome")
    public ModelAndView welcome(){
        ModelAndView model = new ModelAndView("welcome");
        WelcomeModel welcomeModel = this.iHomeService.GetWelcomeData(getUserInfo().getId());
        List<HomeAlarmViewModel> list =new ArrayList<>();
        for (HomeAlarmViewModel h:welcomeModel.getAlarmList()) {
            String upTime = DateUtil.StringToString(h.getUpdateTime(), DateStyle.HH_MM_SS);
            h.setUpdateTime(upTime);
            list.add(h);
        }
        welcomeModel.setAlarmList(list);
        model.getModel().put("obj",welcomeModel);
        return model;
    }

}
