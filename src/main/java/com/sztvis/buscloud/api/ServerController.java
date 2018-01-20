package com.sztvis.buscloud.api;

import com.alibaba.fastjson.JSON;
import com.sztvis.buscloud.core.DateUtil;
import com.sztvis.buscloud.model.CanCommon;
import com.sztvis.buscloud.model.domain.*;
import com.sztvis.buscloud.model.dto.HostApiModel;
import com.sztvis.buscloud.model.dto.api.CanModel;
import com.sztvis.buscloud.model.dto.api.GpsModel;
import com.sztvis.buscloud.model.dto.api.HealthModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.dto.service.SaveAlarmQuery;
import com.sztvis.buscloud.model.entity.DeviceStateFiled;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IBasicService;
import com.sztvis.buscloud.service.ICanService;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IGpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/11 下午4:54
 */
@RequestMapping("/api/v1/server")
@RestController
public class ServerController extends BaseApiController{

    @Autowired
    private ICanService iCanService;
    @Autowired
    private IDeviceService iDeviceService;
    @Autowired
    private IGpsService iGpsService;
    @Autowired
    private IBasicService iBasicService;

    /**
     * 处理客户端主机数据
     * @param apiModel
     * @return
     */
    @RequestMapping("/accept")
    public ApiResult Accept(@RequestBody HostApiModel apiModel){
        ApiResult result =new ApiResult();
        switch (apiModel.getType()){
            case HEALTH:
                result = HealthFunc(apiModel);
                break;
            case GPS:
                result = GpsFunc(apiModel);
                break;
            case CAN:
                result = CanFunc(apiModel);
                break;
            case DISPATCH:

                break;
            case ALARM:

                break;
            case HVNVR:

                break;

            case RADAR:

                break;

            case HARDWARESTATE:

                break;
            case REALTIMEDVRSTATE:

                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 处理心跳
     * @param apiModel
     * @return
     */
    private ApiResult HealthFunc(HostApiModel apiModel){
        HealthModel healthModel = JSON.parseObject(apiModel.getMsgInfo().toString(),HealthModel.class);
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(healthModel.getCode());
        if(deviceInfo == null)
            return ApiResult(false,"不存在编号为"+healthModel.getCode()+"的设备", StatusCodeEnum.DataNotFound,null);
        TramDeviceHealthInfo healthInfo = new TramDeviceHealthInfo();
        healthInfo.setDevicecode(healthModel.getCode());
        healthInfo.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        this.iDeviceService.AddDeviceHealthInfo(healthInfo);
        return  ApiResult(true,"设备"+healthModel.getCode()+"心跳增加成功!",StatusCodeEnum.Success,null);
    }

    /**
     * 处理Gps
     * @param apiModel
     * @return
     */
    private ApiResult GpsFunc(HostApiModel apiModel){
        GpsModel gpsModel = JSON.parseObject(apiModel.getMsgInfo().toString(),GpsModel.class);
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(gpsModel.getCode());
        if(deviceInfo == null)
            return ApiResult(false,"不存在编号为"+gpsModel.getCode()+"的设备",StatusCodeEnum.DataNotFound,null);
        TramGpsInfo gpsInfo = new TramGpsInfo();
        gpsInfo.setDevicecode(gpsModel.getCode());
        gpsInfo.setDeviceid(deviceInfo.getId());
        gpsInfo.setDirection(gpsModel.getDirect());
        gpsInfo.setLatitude(gpsModel.getLat().toString());
        gpsInfo.setLongitude(gpsModel.getLon().toString());
        gpsInfo.setLocationmode(gpsModel.getModel());
        gpsInfo.setSpeed(gpsModel.getSpeed());
        gpsInfo.setUpdatetime(DateUtil.StringToDate(gpsModel.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
        this.iGpsService.saveGpsInfo(gpsInfo);
        return  ApiResult(true,"设备"+gpsModel.getCode()+"Gps增加成功!",StatusCodeEnum.Success,null);
    }

    /**
     * 处理Can数据
     * @param apiModel
     * @return
     */
    private ApiResult CanFunc(HostApiModel apiModel){
        CanModel canModel = JSON.parseObject(apiModel.getMsgInfo().toString(),CanModel.class);
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(canModel.getCode());
        if(deviceInfo == null)
            return ApiResult(false,"不存在编号为"+canModel.getCode()+"的设备",StatusCodeEnum.DataNotFound,null);
        String updateTime = canModel.getUpdateTime().replace("T"," ");
        String[] arr=updateTime.split("\\.");
        updateTime=arr[0];
        List<Integer> AlarmTypesContrast = null;
        List<Integer> ActsContrast = null;
        try {
            ActsContrast = this.iBasicService.getCustomIdsByType(1);
            AlarmTypesContrast = this.iBasicService.getCustomIdsByType(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<Integer,Double> maps = canModel.getValue();
        TramCanInfo canInfo = this.iCanService.GetCanInfo(canModel.getCode(),updateTime);
        if(canInfo == null) {
            canInfo = new TramCanInfo();
            canInfo.setDeviceid(deviceInfo.getId());
            canInfo.setDevicecode(deviceInfo.getDevicecode());
            canInfo.setUpdatetime(updateTime);
            canInfo.setCreatetime(DateUtil.getCurrentTime());
        }
        try {
            for (Integer key : maps.keySet()) {
                //传统车辆CAN数据
                if (CanCommon.NormalCANContrast.containsKey(key)) {
                    Class cls = (Class)canInfo.getClass();
                    Field fs = cls.getDeclaredField(CanCommon.NormalCANContrast.get(key));
                    fs.setAccessible(true);
                    fs.set(canInfo,maps.get(key).toString());
                    this.iCanService.Save(canInfo);
                }
                //BMS纯电动车CAN数据
                if (CanCommon.BmsCANContrast.containsKey(key)) {
                    ElectricCanInfo elec = canInfo.getElectricCanInfo();
                    Class cls = (Class)elec.getClass();
                    Field fs = cls.getDeclaredField(CanCommon.BmsCANContrast.get(key));
                    fs.setAccessible(true);
                    fs.set(elec,maps.get(key));
                    canInfo.setElectricCanInfo(elec);
                    this.iCanService.Save(canInfo);
                }
                //报警
                if (AlarmTypesContrast.contains(key)) {
                    SaveAlarmQuery query = new SaveAlarmQuery();
                    query.setAlarmTime(updateTime);
                    query.setAlarmType(key);
                    query.setDeviceCode(deviceInfo.getDevicecode());
                    query.setDeviceId(deviceInfo.getId());
                    query.setValue(maps.get(key).toString());
                    this.iCanService.AddAlarmInfo(query);
                }
                //can状态
                if (ActsContrast.contains(key)) {
                    TramCanActinfo act = new TramCanActinfo();
                    act.setCustomId(key);
                    act.setValue(maps.get(key).toString());
                    List<TramCanActinfo> acts = new ArrayList<TramCanActinfo>(){{
                        add(act);
                    }};
                    this.iCanService.AddCanActInfo(updateTime,deviceInfo.getDevicecode(),acts);
                }
            }
            //this.iDeviceService.UpdateRealTimeInspect(deviceInfo.getDevicecode(), DeviceStateFiled.CanState,1);
            return  ApiResult(true,"设备"+deviceInfo.getDevicecode()+"Can增加成功!",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return  ApiResult(false,"设备"+deviceInfo.getDevicecode()+"Can增加失败!",StatusCodeEnum.Error,ex.getMessage());
        }
    }

}
