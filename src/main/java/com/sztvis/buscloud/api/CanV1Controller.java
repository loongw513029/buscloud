package com.sztvis.buscloud.api;

import com.sztvis.buscloud.model.domain.TramCanInfo;
import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.dto.CanHistoryViewModel;
import com.sztvis.buscloud.model.dto.CanViewModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.ICanService;
import com.sztvis.buscloud.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/2/1 上午10:40
 */
@RestController
@RequestMapping("/api/v1/can")
public class CanV1Controller extends BaseApiController {
    @Autowired
    private ICanService iCanService;
    @Autowired
    private IDeviceService iDeviceService;
    /**
     * 获取CAN实时数据
     * @param code
     * @return
     */
    @RequestMapping("/realtimedata")
    public ApiResult getCanRealTime(String code){
        CanViewModel canViewModel = this.iCanService.getLastCanViewModel(code);
        return ApiResult(true,"CAN数据获取成功", StatusCodeEnum.Success,canViewModel);
    }

    @RequestMapping("/realtimebyid")
    public ApiResult getCanRealTime(long deviceId){
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoById(deviceId);
        CanViewModel canViewModel = this.iCanService.getLastCanViewModel(deviceInfo.getDevicecode());
        return ApiResult(true,"CAN数据获取成功", StatusCodeEnum.Success,canViewModel);
    }

    @RequestMapping("/getcanhistory")
    public ApiResult getCanHistory(long lineId,int dayType){
        CanHistoryViewModel canViewModel = this.iCanService.getCanHistorys(dayType,lineId);
        return ApiResult(true,"CAN历史获取成功", StatusCodeEnum.Success,canViewModel);
    }

}
