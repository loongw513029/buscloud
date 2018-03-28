package com.sztvis.buscloud.api;

import com.sztvis.buscloud.model.dto.GpsViewModel;
import com.sztvis.buscloud.model.dto.MapBoxModel;
import com.sztvis.buscloud.model.dto.MapHistoryLocationModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IGpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/25 上午8:39
 */
@RestController
@RequestMapping("/api/v1/map")
public class MapV1Controller extends BaseApiController{

    @Autowired
    private IGpsService iGpsService;
    @Autowired
    private IDeviceService iDeviceService;
    /**
     * get web page Map datas
     * @param devices deviceid collection
     * @return
     */
    @RequestMapping("/realtime")
    public ApiResult getRealMap(String devices,String time){
        MapBoxModel mapBoxModel = new MapBoxModel();
        List<GpsViewModel> list = new ArrayList<>();
        String[] deviceIds = devices.split(",");
        for (String deviceId2:deviceIds){
            long deviceId = Long.valueOf(deviceId2);
            list.add(this.iGpsService.getAppGpsViewModel(deviceId,time));
        }
        mapBoxModel.setMaps(list);
        return ApiResult(true,"获得地图数据成功", StatusCodeEnum.Success,mapBoxModel);
    }

    /**
     * 获得页面上历史地图轨迹数据
     * @param deviceId
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/getmaphistory",method = RequestMethod.GET)
    public ApiResult getWebMapHistory(long deviceId,String startTime,String endTime,int page,int rows){
        PageBean<MapHistoryLocationModel> pageBean = this.iGpsService.getMapHistoryGpsList(deviceId,startTime,endTime,page,rows);
        return ApiResult(true, "历史列表获取成功", StatusCodeEnum.Success, pageBean);
    }
}
