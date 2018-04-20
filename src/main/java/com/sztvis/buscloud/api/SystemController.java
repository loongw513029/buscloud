package com.sztvis.buscloud.api;

import com.sztvis.buscloud.model.dto.SiteSettingsInfo;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.dto.response.MenuModel;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.ISiteSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/System")
@RestController
public class SystemController extends BaseApiController{
    @Autowired
    private ISiteSettingService iSiteSettingService;

    /**
     * 保存系统设置内容
     * @return
     */
    @RequestMapping(value = "Save",method = RequestMethod.POST)
    public ApiResult Save(int VideoPlayTime,String ServerIp,String Port,int MapSpeedUse,String SMSReceiver,
      String AppVer,String ApkUrl,String VerNotice,double FlceRecognition,int IndexUnit,int AlarmTipTime,int AlarmTurn,int ADASTime,int AutoPlay,int AlarmRelTime){
        this.iSiteSettingService.SaveSetting("VideoPlayTime",VideoPlayTime);
        this.iSiteSettingService.SaveSetting("ServerIp",ServerIp);
        this.iSiteSettingService.SaveSetting("Port",Port);
        this.iSiteSettingService.SaveSetting("FlceRecognition",FlceRecognition);
        this.iSiteSettingService.SaveSetting("MapSpeedUse",MapSpeedUse);
        this.iSiteSettingService.SaveSetting("SMSReceiver",SMSReceiver);
        this.iSiteSettingService.SaveSetting("AppVer",AppVer);
        this.iSiteSettingService.SaveSetting("ApkUrl",ApkUrl);
        this.iSiteSettingService.SaveSetting("VerNotice",VerNotice);
        this.iSiteSettingService.SaveSetting("IndexUnit",IndexUnit);
        this.iSiteSettingService.SaveSetting("AlarmTipTime",AlarmTipTime);
        this.iSiteSettingService.SaveSetting("AlarmTurn",AlarmTurn);
        this.iSiteSettingService.SaveSetting("ADASTime",ADASTime);
        this.iSiteSettingService.SaveSetting("AutoPlay",AutoPlay);
        this.iSiteSettingService.SaveSetting("AlarmRelTime",AlarmRelTime);
        return ApiResult(true,"保存成功", StatusCodeEnum.Success,null);
    }

    @RequestMapping(value = "Getinfo",method = RequestMethod.GET)
    public ApiResult Getinfo(){
        SiteSettingsInfo info = this.iSiteSettingService.GetSave();
        return ApiResult(true,"获取成功",StatusCodeEnum.Success,info);
    }
}
