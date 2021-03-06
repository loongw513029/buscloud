package com.sztvis.buscloud.api;

import com.github.pagehelper.PageHelper;
import com.sztvis.buscloud.model.UnSafeQuery;
import com.sztvis.buscloud.model.domain.PayTerminalRecords;
import com.sztvis.buscloud.model.domain.TramCanInfo;
import com.sztvis.buscloud.model.domain.TramChannelInfo;
import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.dto.*;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IWorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/15 下午2:56
 */
@RestController
@RequestMapping("/api/v1/device/")
public class DeviceV1Controller extends BaseApiController{

    @Autowired
    private IDeviceService iDeviceService;
    @Autowired
    private IWorkOrderService iWorkOrderService;

    @RequestMapping("/info")
    public ApiResult GetDeviceInfo(long deviceId){
        DeviceViewModel deviceInfo = this.iDeviceService.getDeviceConfig(deviceId);
        return ApiResult(true,"设备信息获取成功", StatusCodeEnum.Success,deviceInfo);
    }

    /**
     * 获得设备巡检数据
     * @return
     */
    @RequestMapping("/inspectlist")
    public ApiResult getDeviceInspect(long userid,long departmentid,long lineid,int type,String keywords,int page,int rows){
        PageHelper.startPage(page,rows);
        List<DeviceInspectViewModel> list = this.iDeviceService.getDeviceInspectList(userid,departmentid,lineid,type,keywords);
        int count = list.size();
        PageBean<DeviceInspectViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "巡检列表获取成功", StatusCodeEnum.Success, pageData);
    }

    /**
     * 获得设备维修列表
     * @param page
     * @param rows
     * @return
     */

    @RequestMapping(value = "/repairlist",method = RequestMethod.GET)
    public ApiResult GetDeviceRepairList(String starttime,String endtime,String code,int page,int rows){
        PageHelper.startPage(page,rows);
        List<WorkOrderViewModel> list=this.iWorkOrderService.GetWorkOrders(code,starttime,endtime);
        int count=list.size();
        PageBean<WorkOrderViewModel> pageData=new PageBean<>(page,rows,count);
        pageData.setItems(list);
        return ApiResult(true, "巡检列表获取成功", StatusCodeEnum.Success, pageData);
    }

    /**
     * 获得单车分析数据
     * @param dayType 时间类型
     * @param deviceId 设备Id
     * @return
     */

    @RequestMapping(value = "/getbusinfo",method = RequestMethod.GET)
    public ApiResult GetBusViewModel(int dayType,long deviceId){
        try {
            AppBusViewModel model = this.iDeviceService.GetAppBusModel(dayType, deviceId);
            return ApiResult(true, "巡检列表获取成功", StatusCodeEnum.Success, model);
        }
        catch (Exception ex){
            return ApiResult(true, "巡检列表获取失败", StatusCodeEnum.Error, null);
        }
    }

    /**
     * 获得通道列表，适用于历史回放界面通道列表
     * @param deviceId
     * @return
     */
    @RequestMapping("/getChannels")
    public ApiResult getChannelsByDeviceId(long deviceId){
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoById(deviceId);
        List<TramChannelInfo> list = this.iDeviceService.GetChannelsByDeviceId(deviceId);
        if(list==null||list.size()==0){
            list = new ArrayList<>();
            for(int i=0;i<deviceInfo.getVideochannel();i++){
                TramChannelInfo channelInfo = new TramChannelInfo();
                channelInfo.setChannelname("通道"+(i+1));
                channelInfo.setNo(i);
                channelInfo.setDeviceid(deviceId);
                channelInfo.setSupportptz(false);
                list.add(channelInfo);
            }
        }
        return ApiResult(true,"获得通道列表成功",StatusCodeEnum.Success,list);
    }

    /**
     * 点击设备获取即时状态
     * @param deviceId
     * @return
     */
    @RequestMapping("/getstatus")
    public ApiResult getDeviceStatus(long deviceId){
        DeviceStatusPushModel model = this.iDeviceService.getCurrentDeviceStatus(deviceId);
        return ApiResult(true,"获得设备状态成功",StatusCodeEnum.Success,model);
    }

    @RequestMapping(value = "/getPayRecords",method = RequestMethod.GET)
    public ApiResult getPayRecords(String CardNo, String date1, String date2, String sitename ,int page ,int rows){
        List<PayTerminalRecords> list = this.iDeviceService.getPayRecords(CardNo, date1, date2, sitename);
        int count = list.size();
        PageBean<PayTerminalRecords> pageBean = new PageBean<>(page, rows, count);
        pageBean.setItems(list);
        return ApiResult(true,"获得支付记录成功",StatusCodeEnum.Success,pageBean);
    }
}
