package com.sztvis.buscloud.api.v2;

import com.sztvis.buscloud.api.BaseApiController;
import com.sztvis.buscloud.model.dto.GpsViewModel;
import com.sztvis.buscloud.model.dto.MapBoxModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IGpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 地图接口V2版本
 * @author longweiqian
 * @company tvis
 * @date 2018/1/25 下午3:13
 */
@RestController
@RequestMapping("/api/v2/map")
public class MapV2Controller extends BaseApiController{
    @Autowired
    private IDeviceService iDeviceService;
    @Autowired
    private IGpsService iGpsService;

    /**
     * get App Data
     * @param codes
     * @return
     */
    @RequestMapping("/maps")
    public ApiResult getMapData(String codes,String time){
        MapBoxModel mapBoxModel = new MapBoxModel();
        List<GpsViewModel> list = new ArrayList<>();
        String[] deviceCodes = codes.split(",");
        for (String deviceCode:deviceCodes){
            long deviceId = this.iDeviceService.getDeviceIdByCode(deviceCode);
            list.add(this.iGpsService.getAppGpsViewModel(deviceId,time));
        }
        mapBoxModel.setMaps(list);
        return ApiResult(true,"获得地图数据成功", StatusCodeEnum.Success,mapBoxModel);
    }
}
