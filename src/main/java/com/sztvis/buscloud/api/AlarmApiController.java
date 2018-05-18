package com.sztvis.buscloud.api;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.model.domain.TramCanInfo;
import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.dto.BicycleViewModel;
import com.sztvis.buscloud.model.dto.CanAlarmInfo;
import com.sztvis.buscloud.model.domain.TramGpsInfo;
import com.sztvis.buscloud.model.dto.AlarmViewModel;
import com.sztvis.buscloud.model.dto.WelcomeTrendModel;
import com.sztvis.buscloud.model.dto.api.AppAlarmChartModel;
import com.sztvis.buscloud.model.dto.api.app.AppAlarmViewModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IAlarmService;
import com.sztvis.buscloud.service.ICanService;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IGpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/23 下午3:31
 */
@RestController
@RequestMapping("/api/v1/alarm")
public class AlarmApiController extends BaseApiController{
    @Autowired
    private IAlarmService iAlarmService;
    @Autowired
    private IGpsService iGpsService;
    @Autowired
    private IDeviceService iDeviceService;
    @Autowired
    private ICanService iCanService;
    /**
     * get webpage alarm table list
     * @param userId userid
     * @param departmentId department id
     * @param lineId line id
     * @param type1 parent alarm type id
     * @param type2 alarm type id
     * @param date1 search start time
     * @param date2 search end time
     * @param keywords search keys contains[alarmname,devicecode]
     * @return
     */
    @RequestMapping(value = "/gettablelist",method = RequestMethod.POST)
    public ApiResult getAlarmList(long userId,long departmentId,long lineId,long type1,long type2,String date1,String date2,String keywords,int page,int rows){
        List<AlarmViewModel> list = this.iAlarmService.getAlarmTableList(userId,departmentId,lineId,type1,type2,date1,date2,keywords,page,rows);
        int count =  this.iAlarmService.getAlarmTableListCount(userId,departmentId,lineId,type1,type2,date1,date2,keywords);
        PageBean<AlarmViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "", StatusCodeEnum.Success, pageData);
    }

    /**
     * get map page's alarm list
     * @param devices deviceid collections,split(',')
     * @param starttime look map start time
     * @return
     */
    @RequestMapping(value = "/getmapalarmlist",method = RequestMethod.GET)
    public ApiResult getMapAlarmList(String devices,String starttime,int page,int rows){
        PageHelper.startPage(page,rows);
        List<AlarmViewModel> list = this.iAlarmService.getMapAlarmTableList(devices,starttime);
        int count = list.size();
        PageBean<AlarmViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "", StatusCodeEnum.Success, pageData);
    }

    @RequestMapping(value = "/list2",method = RequestMethod.GET)
    public ApiResult GetAlarms(long userId,int dayType,long lineId,int type,String code,int page,int limit)
    {
        try {
            long lid=0;
            if (lineId!=0)
                lid=lineId;
            List<AppAlarmViewModel> list=this.iAlarmService.GetList(userId,dayType,0,type,code,lineId,page,limit);
            int count = this.iAlarmService.getAppAlarmListCount(userId,dayType,0,type,code,lineId);
            PageBean<AppAlarmViewModel> pageData=new PageBean<>(page, limit, count);
            pageData.setItems(list);
            return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, pageData);
        }
        catch (Exception ex)
        {
            return ApiResult(false, "获取数据成功", StatusCodeEnum.Error, null);
        }
    }

    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public ApiResult GetAlarmDetailInfo(long Id)
    {
        try {
            CanAlarmInfo info=this.iAlarmService.GetCanAlarmInfo((int)Id);
            TramDeviceInfo info1=this.iDeviceService.GetDriverInfo(info.getDeviceId(),"");
            TramCanInfo canInfo=this.iCanService.GetCanInfoByLastTime(String.valueOf(info.getDeviceId()),info.getYear(),info.getMonth(),info.getDay(),info.getHour(),info.getMinute(),info.getSecond());
            AlarmViewModel model=this.iAlarmService.getAlarmViewModel(Id);
            TramGpsInfo gpsInfo=this.iGpsService.getLastGpsInfo(String.valueOf(info.getDeviceId()),info.getUpdateTime());
            AlarmViewModel viewModel=new AlarmViewModel();
            if (gpsInfo!=null)
                viewModel.setLocation(gpsInfo.getLongitude().concat(",").concat(gpsInfo.getLatitude()));
            else
                viewModel.setLocation("1");
            String extras=info.getExtras();
            if(extras!=null)
            {
                String[] extrasArr=extras.split("|");
                AlarmViewModel.CurrentCanModel canModel=new AlarmViewModel.CurrentCanModel();
                canModel.setDistance(Double.valueOf(extrasArr[1]));
                canModel.setBreak(extrasArr[2]=="2");
                canModel.setSpeed(extrasArr[0]);
                viewModel.setCan(canModel);
            }
            return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, viewModel);
        }
        catch (Exception ex)
        {
            return ApiResult(false, "获取数据失败", StatusCodeEnum.Error, null);
        }
    }



    @RequestMapping(value = "/getalarmcharts",method = RequestMethod.GET)
    public ApiResult GetAlarmCharts(long userId,long lineId,int type)
    {
        try {
            long nullable = 0;
            if (StringHelper.isNotEmpty(lineId) && lineId != 0)
                nullable = lineId;
            AppAlarmChartModel model = this.iAlarmService.GetAppAlarmCharts(userId, nullable, type);
            return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, model);
        }
        catch (Exception ex) {
            return ApiResult(false, "获取数据失败", StatusCodeEnum.Error, null);
        }
    }

    @RequestMapping(value = "/getalarmcharts1",method = RequestMethod.GET)
    public ApiResult GetAlarmCharts1(long lineId,int type1,String type2,String date2,String date3,String code,long departmentId)
    {
        try {
            WelcomeTrendModel model = this.iAlarmService.getAlarmChartList(lineId,type1,type2,date2,date3,code,departmentId);
            return ApiResult(true, "获取数据成功", StatusCodeEnum.Success, model);
        }
        catch (Exception ex) {
            return ApiResult(false, "获取数据失败", StatusCodeEnum.Error, null);
        }
    }
}
