package com.sztvis.buscloud.api;

import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/15 下午2:56
 */
@RestController
@RequestMapping("/api/v1/device/")
public class DeviceController extends BaseApiController{

    @Autowired
    private IDeviceService iDeviceService;

    @RequestMapping("/info")
    public ApiResult GetDeviceInfo(long deviceId){
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoById(deviceId);
        return ApiResult(true,"设备信息获取成功", StatusCodeEnum.Success,deviceInfo);
    }
}
