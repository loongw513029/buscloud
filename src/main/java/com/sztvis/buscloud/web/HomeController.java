package com.sztvis.buscloud.web;

import com.sztvis.buscloud.core.DateStyle;
import com.sztvis.buscloud.core.DateUtil;
import com.sztvis.buscloud.model.dto.HomeAlarmViewModel;
import com.sztvis.buscloud.model.dto.MaintenanceInfo;
import com.sztvis.buscloud.model.dto.SiteSettingsInfo;
import com.sztvis.buscloud.model.dto.WelcomeModel;
import com.sztvis.buscloud.service.IHomeService;
import com.sztvis.buscloud.service.IMaintenanceService;
import com.sztvis.buscloud.service.IMenuService;
import com.sztvis.buscloud.service.ISiteSettingService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
    @Autowired
    private IMaintenanceService iMaintenanceService;
    @Autowired
    private ISiteSettingService iSiteSettingService;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        SiteSettingsInfo info = this.iSiteSettingService.GetSave();
        modelAndView.getModel().put("navs",iMenuService.GetExtNavDataMenu(0L,getUserInfo().getRoleId()));
        modelAndView.getModel().put("user",getUserInfo());
        modelAndView.getModel().put("config",info);
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
        List<MaintenanceInfo> mncs = this.iMaintenanceService.getTop8Maintenaces();
        List<MaintenanceInfo> list2 = new ArrayList<>();
        for (MaintenanceInfo mc:mncs){
            mc.setMtDate(DateUtil.StringToString(mc.MtDate,DateStyle.MM_DD));
            list2.add(mc);
        }
        model.getModel().put("obj",welcomeModel);
        model.getModel().put("mncs",list2);
        return model;
    }

}

