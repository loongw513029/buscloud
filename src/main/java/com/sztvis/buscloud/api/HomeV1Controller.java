package com.sztvis.buscloud.api;


import com.alibaba.fastjson.serializer.JSONSerializer;
import com.fasterxml.jackson.core.JsonFactory;
import com.sun.deploy.net.protocol.ProtocolType;
import com.sun.javafx.tk.Toolkit;
import com.sun.org.apache.xerces.internal.util.Status;
import com.sztvis.buscloud.api.BaseApiController;
import com.sztvis.buscloud.core.DateStyle;
import com.sztvis.buscloud.core.DateUtil;
import com.sztvis.buscloud.model.AppDeviceViewModel;
import com.sztvis.buscloud.model.BaseModel;
import com.sztvis.buscloud.model.HomealramViewModel;
import com.sztvis.buscloud.model.domain.TramChannelInfo;
import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.dto.*;
import com.sztvis.buscloud.model.dto.api.DeviceFilterSearchResult;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.dto.service.ChartViewModel;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.*;
import com.sztvis.buscloud.util.HttpHelp;
import javafx.concurrent.Task;
import jdk.net.SocketFlow;
import org.apache.tomcat.util.modeler.BaseModelMBean;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1/home")
public class HomeV1Controller extends BaseApiController {

    @Autowired
    private ILineService iLineService;

    @Autowired
    private ISiteSettingService iSiteSettingService;

    @Autowired
    private IDeviceService iDeviceService;

    @Autowired
    private ICanService iCanService;

    @Autowired
    private IGpsService iGpsService;

    @Autowired
    private IAlarmService iAlarmService;
    @Autowired
    private IHomeService iHomeService;
    /**
     * 获得首页数据
     * @param departmentId 机构Id
     * @return
     */

    @RequestMapping(value = "/lines",method = RequestMethod.GET)
    public ApiResult GetLines(long userId,long departmentId)
    {
        try {
            List<SelectViewModel> list = this.iLineService.GetDropDownLine(userId);
            List<AppSelectViewModel> list1 = new ArrayList<>();
            for (SelectViewModel r : list) {
                AppSelectViewModel model = new AppSelectViewModel();
                model.setId(r.getId());
                model.setName(r.getValue());
                model.setOnline(true);
                model.setNumData(iLineService.GetAppNumByLineId((long) r.getId()));
                list1.add(model);
            }
            AppHomeViewModel model = new AppHomeViewModel();
            model.setLines(list1);
            return ApiResult(true,"获取数据成功",StatusCodeEnum.Success,model);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            ApiResult result = new ApiResult();
            result.setSuccess(false);
            result.setCode(StatusCodeEnum.Error.toString());
            result.setInfo("获取数据失败！");
            return result;
        }
    }

    /**
     * 获得首页图表数据
     * @param lineId 线路ID
     * @return
     */

    @RequestMapping(value = "/getcharts",method = RequestMethod.GET)
    public ApiResult GetHomeCharts(long userId,long lineId)
    {
        try {
            ChartViewModel model = this.iSiteSettingService.GetAppCharts(userId, lineId);
            return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, model);
        }
        catch (Exception ex)
        {
            return ApiResult(false, "获取数据成功", StatusCodeEnum.Error, null);
        }
    }
    /**
     * 获得web页面趋势数据
     * @param userId
     * @return
     */
    @RequestMapping("/getWebTrends")
    public ApiResult getWebTrends(long userId){
        try {
            WelcomeTrendModel welcomeTrendModel = this.iHomeService.getWelcomeTrendModels(userId);
            return ApiResult(true, "获取WEB页面趋势数据成功", StatusCodeEnum.Success, welcomeTrendModel);
        }
        catch (Exception ex){
            return ApiResult(false, "获取WEB页面趋势数据失败", StatusCodeEnum.Error, ex.getMessage());
        }
    }
    /**
     * 根据线路获得设备列表信息
     * @param lineId 线路ID
     * @return
     */

    @RequestMapping(value = "/getdevicesbyline",method = RequestMethod.GET)
    public ApiResult GetDevicesByLine(long lindeId)
    {
        try {
            List<TramDeviceInfo> list=this.iLineService.getDevices(lindeId,0);
            List<AppDropDownModel> info=new ArrayList<>();
            for(TramDeviceInfo r:list)
            {
                AppDropDownModel model=new AppDropDownModel();
                model.setId(r.getId());
                model.setName(r.getDevicecode());
                model.setOnline(Boolean.valueOf(r.getDevicestatus()==1));
                info.add(model);
            }
            return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, info);
        }
        catch (Exception ex)
        {
            return ApiResult(false, "获取数据失败", StatusCodeEnum.Error, null);
        }
    }

    /**
     * 根据设备id获得该设备的视频通道列表
     * @param deviceId 设备ID
     * @return
     */

    @RequestMapping(value = "/getchannelbydevice",method = RequestMethod.GET)
    public ApiResult GetChannelsByDevice(long deviceId)
    {
        try {
            List<TramChannelInfo> list=this.iLineService.GetChannlsByDeviceId(deviceId);
            List<AppDropDownModel> info=new ArrayList<>();
            for(TramChannelInfo r:list)
            {
                AppDropDownModel model = new AppDropDownModel();
                model.setId(r.getNo());
                model.setName(r.getChannelname());
                info.add(model);
            }
            return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, info);
        }
        catch (Exception ex)
        {
            return ApiResult(false, "获取数据失败", StatusCodeEnum.Error, null);
        }
    }

    /**
     * 根据线路获得设备列表列表
     * @param lineId 线路ID
     * @return
     */

    @RequestMapping(value = "/devices",method = RequestMethod.GET)
    public ApiResult GetDeviceListByLine(long lineId,long userId)
    {
        try {
            List<TramDeviceInfo> list=this.iLineService.getDevices(lineId,userId);
            List<AppDeviceViewModel> info=new ArrayList<>();
            for(TramDeviceInfo r:list)
            {
                AppDeviceViewModel model=new AppDeviceViewModel();
                model.setId(r.getId());
                model.setDeviceCode(r.getDevicecode());
                model.setDeviceName(r.getDevicename());
                model.setOnline(Boolean.valueOf(r.getDevicestatus()==1));
                model.setDeviceName("");
                info.add(model);
            }
            return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, info);
        }
        catch (Exception ex)
        {
            return ApiResult(false, "获取数据失败", StatusCodeEnum.Error, null);
        }
    }

    /**
     * 获得视频参数
     * @param deviceId
     * @return
     */

    @RequestMapping(value = "/getdeviceconfig",method = RequestMethod.GET)
    public ApiResult GetVideoConfig(long deviceId) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, JSONException {
        TramDeviceInfo model=this.iDeviceService.GetDriverInfo(deviceId,"");
        SiteSettingsInfo info=this.iSiteSettingService.GetSiteSettings(1);
        String result= HttpHelp.sendHttpGet("http://"+info.getServerIp()+":9200/Transmit/Server?Type=AlarmRecordSavePath").toString();
        JSONObject object=new JSONObject(result);
        Map<String,Object> map=new HashMap<>();
        map.put("devicecode",model.getDevicecode());
        map.put("mac",model.getMachine());
        map.put("channel",model.getVideochannel());
        map.put("dchannel",model.getDchannel());
        map.put("fchannel",model.getCarriagechannel());
        map.put("channellist",iDeviceService.GetChannelsByDeviceId(deviceId));
        map.put("serverip",info.getServerIp());
        map.put("clientip",model.getClientip());
        map.put("hosttype",model.getHostsofttype()==0?"NVR":"TongLi");
        map.put("path",object.get("Msg"));
        if (map.size()-9==0)
        {
            List<TramChannelInfo> clist=new ArrayList<>();
            for (int i=0;i<8;i++)
            {
                TramChannelInfo channelInfo=new TramChannelInfo();
                channelInfo.setChannelname("通道"+(i+1));
                channelInfo.setDeviceid(model.getId());
                channelInfo.setId(new Long(i));
                channelInfo.setNo(i);
                channelInfo.setSupportptz(false);
                clist.add(channelInfo);
            }
        }
        return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, map);
    }

    @RequestMapping(value = "/sendSocket",method = RequestMethod.GET)
    public ApiResult SendSocket(long deviceId,int channel,int turn) throws IOException {
        TramDeviceInfo info=this.iDeviceService.GetDriverInfo(deviceId,"");
        BaseModel model=new BaseModel();
        model.setType(1);
        model.setTarget(info.getMachine());
        model.setSource("appsocket");
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("code",info.getDevicecode());
        map.put("ch",channel);
        map.put("turn",turn);
        model.setMsgInfo(map);
        Socket socket=new Socket("localhost",8080);
        InputStream inputStream=socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        String message=model.toString();
        socket.getOutputStream().write(message.getBytes("UTF-8"));
        outputStream.close();
        socket.close();
        return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, null);
    }

    @RequestMapping(value = "/getappconfig",method = RequestMethod.GET)
    public ApiResult GetNetWorkAppVersion(long userId) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        HomealramViewModel.ViewModel viewModel=iAlarmService.GetTop10Alarms(userId);
        SiteSettingsInfo info=this.iSiteSettingService.GetSiteSettings(1);
        List<HomealramViewModel> list=viewModel.getView();
        Map<String,Object> map=new HashMap<>();
        map.put("ver",info.getAppVer());
        map.put("url",info.getApkUrl());
        map.put("desc",info.getVerNotice());
        map.put("num",viewModel.getCount());
        return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, map);
    }

    /**
     * 获得即时CAN或GPS状态
     * @param devicecode
     * @return
     */

    @RequestMapping(value = "/getcanorgpsstate",method = RequestMethod.GET)
    public ApiResult GetDeviceCanOrGpsState(String devicecode)
    {
        try {
            TramDeviceInfo device=this.iDeviceService.GetDriverInfo(0,devicecode);
            int canC=this.iCanService.GetCanInfoBy10sTime(device.getDevicecode(),DateUtil.getCurrentTime(DateStyle.YYYY_MM_DD_HH_MM_SS),60);
            Long gpsC=this.iGpsService.GetCountBy10sGps(device.getId(),DateUtil.getCurrentTime(DateStyle.YYYY_MM_DD_HH_MM_SS),60);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("canstate",canC>0);
            map.put("gpsstate",gpsC>0);
            return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, map);
        }
        catch (Exception ex) {
            return ApiResult(false, "获取数据失败", StatusCodeEnum.Error, ex.getMessage());
        }
    }

    /**
     * 设备筛选界面搜索设备编码
     * @param code
     * @return
     */

    @RequestMapping(value = "/devicesearch",method = RequestMethod.GET)
    public ApiResult GetAppDeviceSearch(String code)
    {
        try {
            DeviceFilterSearchResult model = this.iDeviceService.GetAppDeviceFilterSearch(code);
            return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, model);
        }
        catch (Exception ex) {
            return ApiResult(false, "获取数据失败", StatusCodeEnum.Error, null);
        }
    }
}
