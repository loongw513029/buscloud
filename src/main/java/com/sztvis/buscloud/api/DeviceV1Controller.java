package com.sztvis.buscloud.api;

import com.github.pagehelper.PageHelper;
import com.sztvis.buscloud.model.UnSafeQuery;
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
     * @param lineId 线路Id
     * @param departmentId 用户结构Id
     * @param page 页码
     * @param limit 页数
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
     * @param userId 用户Id
     * @param page
     * @param limit
     * @return
     */

    @RequestMapping(value = "/repairlist",method = RequestMethod.GET)
    public ApiResult GetDeviceRepairList(long userid,int page,int limit){
        PageHelper.startPage(page,limit);
        List<WorkOrderViewModel> list=this.iWorkOrderService.GetWorkOrders(userid,1);
        int count=list.size();
        PageBean<WorkOrderViewModel> pageData=new PageBean<>(page,limit,count);
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


}
