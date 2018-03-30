package com.sztvis.buscloud.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sztvis.buscloud.core.DateStyle;
import com.sztvis.buscloud.core.DateUtil;
import com.sztvis.buscloud.core.helper.ImageHelper;
import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.model.CanCommon;
import com.sztvis.buscloud.model.domain.*;
import com.sztvis.buscloud.model.dto.HostApiModel;
import com.sztvis.buscloud.model.dto.InspectView;
import com.sztvis.buscloud.model.dto.PassengerFlowView;
import com.sztvis.buscloud.model.dto.PayTerminalView;
import com.sztvis.buscloud.model.dto.api.*;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.dto.service.SaveAlarmQuery;
import com.sztvis.buscloud.model.entity.DeviceStateFiled;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.*;
import org.apache.ibatis.javassist.bytecode.ClassFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

/**
 * 用于处理各种主机上传数据
 * @author longweiqian
 * @company tvis
 * @date 2018/1/11 下午4:54
 */
@RequestMapping("/api/v1/server")
@RestController
public class ServerController extends BaseApiController{
    private final Map<Integer,String> map1 = new HashMap<Integer, String>(){{
        put(1,"ADAS串口丢失");
        put(2,"CAN串口丢失");
        put(3,"调度串口丢失");
        put(4,"雷达串口丢失");
        put(5,"行为识别串口丢失");
    }};

    @Value("${upload.dir}")
    private String filePath;
    @Autowired
    private ICanService iCanService;
    @Autowired
    private IDeviceService iDeviceService;
    @Autowired
    private IGpsService iGpsService;
    @Autowired
    private IBasicService iBasicService;
    @Autowired
    private IDispatchService iDispatchService;
    @Autowired
    private IInspectService iInspectService;

    /**
     * 处理客户端主机数据
     * @param apiModel
     * @return
     */
    @RequestMapping("/accept")
    public ApiResult Accept(@RequestBody HostApiModel apiModel,HttpServletRequest request) throws NoSuchFieldException, IllegalAccessException, ParseException {
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
                result = DispatchFunc(apiModel);
                break;
            case ALARM:
                result = AlarmFunc(apiModel,request);
                break;
            case HVNVR:
                result = DeviceStatusFunc(apiModel);
                break;
            case RADAR:
                result = RadarFunc(apiModel);
                break;
            case HARDWARESTATE:
                result = HardWareFunc(apiModel);
                break;
            case REALTIMEDVRSTATE:
                result = RealTimeStateFunc(apiModel);
                break;
            case KL:

                break;
            case INSPECT:
                result = OneKeyInspectFunc(apiModel,request);
                break;
            case PAY_TERMINAL:
                result = PayRecordFunc(apiModel,request);
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
        return ApiResult(true,"设备"+healthModel.getCode()+"心跳增加成功!",StatusCodeEnum.Success,null);
    }

    /**
     * 处理Gps
     * @param apiModel
     * @return
     */
    private ApiResult GpsFunc(HostApiModel apiModel) throws ParseException {
        GpsModel gpsModel = JSON.parseObject(apiModel.getMsgInfo().toString(),GpsModel.class);
        gpsModel.setUpdateTime(DateUtil.getTimestampStr(gpsModel.getUpdateTime()));
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
        gpsInfo.setUpdatetime(gpsModel.getUpdateTime());
        this.iGpsService.saveGpsInfo(gpsInfo);
        this.iDeviceService.UpdateRealTimeInspect(gpsModel.getCode(),DeviceStateFiled.GpsState,true,3);
        this.iDeviceService.UpdateRealTimeInspect(gpsModel.getCode(),DeviceStateFiled.GpsInspectState,true,3);
        this.iDeviceService.UpdateRealTimeInspect(gpsModel.getCode(),DeviceStateFiled.OnlineState,true,3);
        return ApiResult(true,"设备"+gpsModel.getCode()+"Gps增加成功!",StatusCodeEnum.Success,null);
    }

    /**
     * 处理Can数据
     * @param apiModel
     * @return
     */
    private ApiResult CanFunc(HostApiModel apiModel) throws ParseException {
        CanModel canModel = JSON.parseObject(apiModel.getMsgInfo().toString(),CanModel.class);
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(canModel.getCode());
        if(deviceInfo == null)
            return ApiResult(false,"不存在编号为"+canModel.getCode()+"的设备",StatusCodeEnum.DataNotFound,null);
        String updateTime = DateUtil.getTimestampStr(canModel.getUpdateTime()).toString();
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
                if(key == 212) key = 42;
                if(key == 213) key = 1;
                if(key == 214) key = 2;
                if(key == 217) key = 3;
                if(key == 248||key ==249) key = 82;
                if(key == 253) key = 42;
                if(key == 259) key = 88;
                if(key == 260) key = 87;
                if(key == 261) key = 14;
                if(key == 262) key = 15;
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
            //运算不安全行为
            this.iCanService.autoCalcUnsafeData(deviceInfo.getId(),updateTime);
            //this.iDeviceService.UpdateRealTimeInspect(deviceInfo.getDevicecode(), DeviceStateFiled.CanState,1);
            return  ApiResult(true,"设备"+deviceInfo.getDevicecode()+"Can增加成功!",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return  ApiResult(false,"设备"+deviceInfo.getDevicecode()+"Can增加失败!",StatusCodeEnum.Error,ex.getMessage());
        }
    }

    /**
     * 处理报警信息
     * @param apiModel
     * @return
     */
    private ApiResult AlarmFunc(HostApiModel apiModel,HttpServletRequest request) throws NoSuchFieldException, IllegalAccessException, ParseException {
        AlarmModel alarmModel = JSON.parseObject(apiModel.getMsgInfo().toString(),AlarmModel.class);
        alarmModel.setUpdateTime(DateUtil.getTimestampStr(alarmModel.getUpdateTime()));
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(alarmModel.getCode());
        if(deviceInfo == null)
            return ApiResult(false,"不存在编号为"+alarmModel.getCode()+"的设备",StatusCodeEnum.DataNotFound,null);
        try {
            //串口丢失报警
            if (map1.keySet().contains(alarmModel.getType())) {
                this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(), deviceInfo.getId(), alarmModel.getUpdateTime(), 1, map1.get(alarmModel.getType()), "", alarmModel.getPath()));
            }
            //雷达报警
            if (alarmModel.getType() == 109) {
                Map<Integer, Integer> map = JSON.parseObject(alarmModel.getValue1().toString(), new TypeReference<Map<Integer, Integer>>() {
                });
                for (Integer i : map.keySet()) {
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(), deviceInfo.getId(), alarmModel.getUpdateTime(), alarmModel.getType(), map.get(i).toString(), "", alarmModel.getPath()));
                }
                //添加雷达数据
                TramRadarInfo radarInfo = new TramRadarInfo();
                radarInfo.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
                radarInfo.setUpdatetime(Timestamp.valueOf(alarmModel.getUpdateTime()));
                radarInfo.setCreatetime(new Timestamp(System.currentTimeMillis()));
                Class cls = radarInfo.getClass();
                for (int i = 0; i < 16; i++) {
                    Field fs = cls.getDeclaredField("radar" + (i + 1));
                    fs.setAccessible(true);
                    if (map.keySet().contains(i + 1))
                        fs.set(radarInfo, map.get(i + 1));
                    else
                        fs.set(radarInfo, 255);
                }
                this.iDeviceService.insertRadarInfo(radarInfo);
                //推送雷达报警
            }
            switch (alarmModel.getType()) {
                case 11://车辆倾斜
                case 12://紧急按钮
                case 13://生命感知
                case 125://急加速
                case 126://急减速
                case 127://急转弯
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(), deviceInfo.getId(), alarmModel.getUpdateTime(), alarmModel.getType(), "0", "", alarmModel.getPath()));
                    break;
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108://行为识别->END
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124://ADAS->END
                    //ADAS,行为识别
                    String[] pics = JSON.parseObject(alarmModel.getValue1().toString(), new TypeReference<String[]>() {
                    });
                    String imgFileName = UUID.randomUUID().toString().replaceAll("-", "")+"_"+alarmModel.getCode()+"_"+alarmModel.getUpdateTime().replaceAll(":","").replace(" ","").replaceAll("-","");
                    ImageHelper.generateImage(pics[0], "imgupload/ADAS/", imgFileName + "_0.jpg",request);
                    ImageHelper.generateImage(pics[1], "imgupload/ADAS/", imgFileName + "_1.jpg",request);
                    String imgValue1 = "imgupload/ADAS/" + imgFileName + "_0.jpg", imgValue2 = "imgupload/ADAS/" + imgFileName + "_1.jpg";
                    List<Double> extras = JSON.parseObject(alarmModel.getValue2().toString(), new TypeReference<List<Double>>() {
                    });
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(), deviceInfo.getId(), alarmModel.getUpdateTime(), alarmModel.getType(), imgValue1 + "," + imgValue2, StringHelper.listToString(extras, ','), alarmModel.getPath()));
                    break;
                case 9999:
                    //ADAS恢复，只需要将信息推送给页面

                    break;
            }
            this.iDeviceService.UpdateRealTimeInspect(alarmModel.getCode(),DeviceStateFiled.CanState,true,3);
            this.iDeviceService.UpdateRealTimeInspect(alarmModel.getCode(),DeviceStateFiled.CanInspectState,true,3);
            this.iDeviceService.UpdateRealTimeInspect(alarmModel.getCode(),DeviceStateFiled.OnlineState,true,3);
            return ApiResult(true,alarmModel.getCode()+"报警数据添加成功",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return ApiResult(false,alarmModel.getCode()+"报警数据添加失败",StatusCodeEnum.Error,ex.getMessage());
        }
    }

    /**
     * 处理调度信息
     * @param apiModel
     * @return
     */
    private ApiResult DispatchFunc(HostApiModel apiModel) throws ParseException {
        DispatchModel dispatchModel = JSON.parseObject(apiModel.getMsgInfo().toString(),DispatchModel.class);
        dispatchModel.setUpdateTime(DateUtil.getTimestampStr(dispatchModel.getUpdateTime()));
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(dispatchModel.getCode());
        if(deviceInfo == null)
            return ApiResult(false,"不存在编号为"+dispatchModel.getCode()+"的设备",StatusCodeEnum.DataNotFound,null);
        try {
            TramDispatchInfo dispatchInfo = new TramDispatchInfo();
            dispatchInfo.setAnalytical(0L);
            dispatchInfo.setCreatetime(DateUtil.getCurrentTime());
            dispatchInfo.setDevicecode(dispatchModel.getCode());
            dispatchInfo.setDeviceid(deviceInfo.getId());
            dispatchInfo.setDispatchname(dispatchModel.getValue3());
            dispatchInfo.setDispatchno(new Long(dispatchModel.getNum()));
            dispatchInfo.setDrivingdirection(new Long(dispatchModel.getValue2()));
            dispatchInfo.setDispatchtype(new Long(dispatchModel.getValue1()));
            dispatchInfo.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
            dispatchInfo.setImage("");
            dispatchInfo.setUpdatetime(dispatchModel.getUpdateTime());
            this.iDispatchService.insertDispatchInfo(dispatchInfo);
            return ApiResult(true,dispatchModel.getCode()+"调度添加成功",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return ApiResult(false,dispatchModel.getCode()+"调度添加失败",StatusCodeEnum.Error,null);
        }
    }

    /**
     * 处理NVR状态
     * @param apiModel
     * @return
     */
    private ApiResult DeviceStatusFunc(HostApiModel apiModel) throws ParseException {
        HVNVRModel hvnvrModel = JSON.parseObject(apiModel.getMsgInfo().toString(),HVNVRModel.class);
        hvnvrModel.setUpdateTime(DateUtil.getTimestampStr(hvnvrModel.getUpdateTime()));
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(hvnvrModel.getCode());
        if(deviceInfo == null)
            return ApiResult(false,"不存在编号为"+hvnvrModel.getCode()+"的设备",StatusCodeEnum.DataNotFound,null);
        try {
            TramDeviceStatusInfo deviceStatusInfo = new TramDeviceStatusInfo();
            deviceStatusInfo.setCreatetime(DateUtil.getCurrentTime());
            deviceStatusInfo.setDevicecode(deviceInfo.getDevicecode());
            deviceStatusInfo.setDeviceid(deviceInfo.getId());
            deviceStatusInfo.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
            deviceStatusInfo.setType(new Long(hvnvrModel.getType()));
            deviceStatusInfo.setValue1(hvnvrModel.getValue1());
            deviceStatusInfo.setValue2(hvnvrModel.getValue2());
            deviceStatusInfo.setUpdatetime(hvnvrModel.getUpdateTime());
            this.iDeviceService.insertDeviceStatusInfo(deviceStatusInfo);
            this.iDeviceService.updateDeviceNvrStatus(deviceInfo, hvnvrModel);
            return ApiResult(true,hvnvrModel.getCode()+"NVR状态添加成功",StatusCodeEnum.Success,null);
        }
        catch (Exception ex){
            return ApiResult(false,hvnvrModel.getCode()+"NVR状态添加失败",StatusCodeEnum.Error,ex.getMessage());
        }
    }

    /**
     * 添加雷达数据
     * @param apiModel
     * @return
     * @throws ParseException
     */
    private ApiResult RadarFunc(HostApiModel apiModel) throws ParseException {
        RadarModel radarModel = JSON.parseObject(apiModel.getMsgInfo().toString(),RadarModel.class);
        radarModel.setUpdateTime(DateUtil.getTimestampStr(radarModel.getUpdateTime()));
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(radarModel.getCode());
        if(deviceInfo == null)
            return ApiResult(false,"不存在编号为"+radarModel.getCode()+"的设备",StatusCodeEnum.DataNotFound,null);
        try{
            TramRadarInfo radarInfo = new TramRadarInfo();
            radarInfo.setDevicecode(deviceInfo.getDevicecode());
            radarInfo.setUpdatetime(Timestamp.valueOf(radarModel.getUpdateTime()));
            radarInfo.setCreatetime(Timestamp.valueOf(DateUtil.getCurrentTime()));
            radarInfo.setGuid(UUID.randomUUID().toString().replaceAll("-",""));
            radarInfo.setDeviceid(deviceInfo.getId());
            Class cls = radarInfo.getClass();
            for(Integer i:radarModel.getValue().keySet()) {
                Field field = cls.getDeclaredField("Radar"+(i+1));
                field.setAccessible(true);
                field.set(radarInfo,radarModel.getValue().get(i));
            }
            this.iDeviceService.insertRadarInfo(radarInfo);
            return ApiResult(true,radarModel.getCode()+"雷达数据添加成功",StatusCodeEnum.Success,null);
        }
        catch (Exception ex){
            return  ApiResult(false,radarModel.getCode()+"雷达数据添加成功",StatusCodeEnum.Error,ex.getMessage());
        }
    }

    /**
     * 硬件数据处理
     * @param apiModel
     * @return
     * @throws ParseException
     */
    private ApiResult HardWareFunc(HostApiModel apiModel) throws ParseException {
        HardWareModel hardWareModel = JSON.parseObject(apiModel.getMsgInfo().toString(),HardWareModel.class);
        hardWareModel.setUpdateTime(DateUtil.getTimestampStr(hardWareModel.getUpdateTime()));
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(hardWareModel.getCode());
        if(deviceInfo == null)
            return ApiResult(false,"不存在编号为"+hardWareModel.getCode()+"的设备",StatusCodeEnum.DataNotFound,null);
        try {
            TramDeviceStatusInfo deviceStatusInfo = new TramDeviceStatusInfo();
            deviceStatusInfo.setCreatetime(DateUtil.getCurrentTime());
            deviceStatusInfo.setDevicecode(deviceInfo.getDevicecode());
            deviceStatusInfo.setDeviceid(deviceInfo.getId());
            deviceStatusInfo.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
            deviceStatusInfo.setType(new Long(hardWareModel.getType())+7);
            deviceStatusInfo.setValue1("True");
            deviceStatusInfo.setValue2(hardWareModel.getValue()+"");
            deviceStatusInfo.setUpdatetime(hardWareModel.getUpdateTime());
            this.iDeviceService.insertDeviceStatusInfo(deviceStatusInfo);
            HVNVRModel hvnvrModel = new HVNVRModel();
            hvnvrModel.setCode(hardWareModel.getCode());
            hvnvrModel.setUpdateTime(hardWareModel.getUpdateTime());
            hvnvrModel.setType(hardWareModel.getType() + 7);
            hvnvrModel.setValue1("True");
            hvnvrModel.setValue2(hardWareModel.getValue() + "");
            this.iDeviceService.updateDeviceNvrStatus(deviceInfo, hvnvrModel);
            return ApiResult(true,hardWareModel.getCode()+"硬件数据添加成功",StatusCodeEnum.Success,null);
        }
        catch (Exception ex){
            return  ApiResult(false,hardWareModel.getCode()+"硬件数据添加成功",StatusCodeEnum.Error,ex.getMessage());
        }
    }

    private ApiResult RealTimeStateFunc(HostApiModel apiModel){
        try {
            List<RealTimeModel> realTimeModel = JSON.parseArray(apiModel.getMsgInfo().toString(), RealTimeModel.class);
            for (RealTimeModel m : realTimeModel) {
                if (m.isOnline()) {
                    TramDeviceHealthInfo healthInfo = new TramDeviceHealthInfo();
                    healthInfo.setDevicecode(m.getCode());
                    healthInfo.setUpdatetime(new Timestamp(System.currentTimeMillis()));
                    this.iDeviceService.AddDeviceHealthInfo(healthInfo);
                }
            }
            return ApiResult(true,"实时DVR状态成功",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return ApiResult(false,"实时DVR状态失败",StatusCodeEnum.Error,ex.getMessage());
        }
    }

    private ApiResult PayRecordFunc(HostApiModel apiModel,HttpServletRequest request) throws ParseException {
        PayTerminalView payTerminalView = JSON.parseObject(apiModel.getMsgInfo().toString(),PayTerminalView.class);
        payTerminalView.setUpdatetime(DateUtil.getTimestampStr(payTerminalView.getUpdatetime()));
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(payTerminalView.getDevicecode());
        if(deviceInfo == null)
            return ApiResult(false,"不存在编号为"+payTerminalView.getDevicecode()+"的设备",StatusCodeEnum.DataNotFound,null);
        try{
            String fileRoot = "imgupload/payterminal/";
            String filenames= DateUtil.StringToString(DateUtil.getCurrentTime(), DateStyle.YYYY_MM_DD_HH_SS_SSS).replaceAll("-","").replaceAll(" ","").replaceAll(":","")+ 1 + ".png";
            ImageHelper.generateImage(payTerminalView.getPassengerimage(),fileRoot,filenames,request);
            String outFileName = "/"+fileRoot+filenames;
            PayTerminalRecords payTerminalRecords =new PayTerminalRecords();
            payTerminalRecords.setDeviceCode(deviceInfo.getDevicecode());
            payTerminalRecords.setDeviceId(deviceInfo.getId());
            payTerminalRecords.setLocation(payTerminalView.getLocation());
            payTerminalRecords.setPassengerImage(outFileName);
            payTerminalRecords.setPayCardNo(payTerminalView.getPaycardno());
            payTerminalRecords.setPayTime(payTerminalView.getPaytime());
            payTerminalRecords.setSiteName(payTerminalView.getSitename());
            payTerminalRecords.setUpdateTime(payTerminalView.getUpdatetime());
            this.iDeviceService.insertPayTerminalRecords(payTerminalRecords);
            return ApiResult(true,"保存刷卡记录成功",StatusCodeEnum.Success,null);
        }
        catch (Exception ex){
            return ApiResult(false,"保存刷卡记录失败",StatusCodeEnum.Success,ex.getMessage());
        }
    }

    private ApiResult OneKeyInspectFunc(HostApiModel apiModel,HttpServletRequest request) throws ParseException {
        InspectView inspectView = JSON.parseObject(apiModel.getMsgInfo().toString(),InspectView.class);
        inspectView.setUpdatetime(DateUtil.getTimestampStr(inspectView.getUpdatetime()));
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(inspectView.getDevicecode());
        if(deviceInfo == null)
            return ApiResult(false,"不存在编号为"+inspectView.getDevicecode()+"的设备",StatusCodeEnum.DataNotFound,null);
        try{
            String fileRoot = "imgupload/Inspect/";
            List<String> imgList = new ArrayList<>();
            for (String base64str:inspectView.getCarimage()) {
                String filenames= DateUtil.StringToString(DateUtil.getCurrentTime(), DateStyle.YYYY_MM_DD_HH_SS_SSS).replaceAll("-","").replaceAll(" ","").replaceAll(":","")+ 1 + ".png";
                ImageHelper.generateImage(base64str,fileRoot,filenames,request);
                String outFileName = "/"+fileRoot+filenames;
                imgList.add(outFileName);
            }
            OneKeyInspectRecords oneKeyInspectRecords = new OneKeyInspectRecords();
            oneKeyInspectRecords.setInspectPics(StringHelper.listToString(imgList,','));
            oneKeyInspectRecords.setDeviceId(deviceInfo.getId());
            oneKeyInspectRecords.setUpdateTime(inspectView.getUpdatetime());
            this.iInspectService.insertOneKeyInspectRecords(oneKeyInspectRecords);
            return ApiResult(true,"一键巡检成功",StatusCodeEnum.Success,null);
        }
        catch (Exception ex){
            return ApiResult(false,"一键巡检失败",StatusCodeEnum.Success,ex.getMessage());
        }
    }

    private ApiResult KLFunc(HostApiModel apiModel) throws ParseException {
        PassengerFlowView passengerFlowView = JSON.parseObject(apiModel.getMsgInfo().toString(),PassengerFlowView.class);
        passengerFlowView.setUpdatetime(DateUtil.getTimestampStr(passengerFlowView.getUpdatetime()));
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoByCode(passengerFlowView.getDevicecode());
        if(deviceInfo == null)
            return ApiResult(false,"不存在编号为"+passengerFlowView.getDevicecode()+"的设备",StatusCodeEnum.DataNotFound,null);
        try{

            OneKeyInspectRecords oneKeyInspectRecords = new OneKeyInspectRecords();
            oneKeyInspectRecords.setInspectPics(StringHelper.listToString(imgList,','));
            oneKeyInspectRecords.setDeviceId(deviceInfo.getId());
            oneKeyInspectRecords.setUpdateTime(inspectView.getUpdatetime());
            this.iInspectService.insertOneKeyInspectRecords(oneKeyInspectRecords);
            return ApiResult(true,"一键巡检成功",StatusCodeEnum.Success,null);
        }
        catch (Exception ex){
            return ApiResult(false,"一键巡检失败",StatusCodeEnum.Success,ex.getMessage());
        }
    }

}
