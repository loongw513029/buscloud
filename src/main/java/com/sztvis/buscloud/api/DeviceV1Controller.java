package com.sztvis.buscloud.api;

import com.github.pagehelper.PageHelper;
import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.dto.DeviceInspectViewModel;
import com.sztvis.buscloud.model.dto.DeviceViewModel;
import com.sztvis.buscloud.model.dto.LineViewModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/info")
    public ApiResult GetDeviceInfo(long deviceId){
        DeviceViewModel deviceInfo = this.iDeviceService.getDeviceConfig(deviceId);
        return ApiResult(true,"设备信息获取成功", StatusCodeEnum.Success,deviceInfo);
    }

    @RequestMapping("/inspectlist")
    public ApiResult getDeviceInspect(long userid,long departmentid,long lineid,int type,String keywords,int page,int rows){
        PageHelper.startPage(page,rows);
        List<DeviceInspectViewModel> list = this.iDeviceService.getDeviceInspectList(userid,departmentid,lineid,type,keywords);
        int count = list.size();
        PageBean<DeviceInspectViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "巡检列表获取成功", StatusCodeEnum.Success, pageData);
    }
}
