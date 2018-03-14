package com.sztvis.buscloud.service.Impl;

import com.alibaba.fastjson.JSON;
import com.sztvis.buscloud.core.DateStyle;
import com.sztvis.buscloud.core.DateUtil;
import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.mapper.AlarmMapper;
import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.mapper.DriverMapper;
import com.sztvis.buscloud.model.domain.*;
import com.sztvis.buscloud.model.dto.*;
import com.sztvis.buscloud.model.dto.api.HVNVRModel;
import com.sztvis.buscloud.model.dto.push.PushModel;
import com.sztvis.buscloud.model.entity.BusType;
import com.sztvis.buscloud.model.entity.DeviceStateFiled;
import com.sztvis.buscloud.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午6:30
 */

@Service
public class DeviceService implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private AlarmMapper alarmMapper;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private IDepartmentService iDepartmentService;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private ICanService iCanService;
    @Autowired
    private IBasicService iBasicService;
    @Autowired
    private IGpsService iGpsService;
    @Autowired
    private IPushService iPushService;

    @Override
    public TramDeviceInfo getDeviceInfoByCode(String deviceCode) {

        return this.deviceMapper.getDeviceInfoByCode(deviceCode);
    }

    @Override
    public TramDeviceInfo getDeviceInfoById(long Id) {

        return this.deviceMapper.getDeviceInfoById(Id);
    }

    @Override
    public long getDeviceIdByCode(String deviceCode) {
        return this.deviceMapper.getDeviceIdByCode(deviceCode);
    }

    @Override
    public List<TramDeviceInfo> GetDevicesByLineId(long lineId) {
        return deviceMapper.getDevicesByLineId(lineId);
    }

    @Override
    public void AddDeviceHealthInfo(TramDeviceHealthInfo healthInfo) {
        TramGpsInfo gpsInfo = this.iGpsService.getLastGpsInfo(healthInfo.getDevicecode());
        if(gpsInfo!=null)
            healthInfo.setLocation(gpsInfo.getLongitude()+","+gpsInfo.getLatitude());
        else
            healthInfo.setLocation("");
        this.mongoTemplate.save(healthInfo);
        //有心跳表示在线，下面改变设备的状态
        this.UpdateDeviceStatus(healthInfo.getDevicecode(),true);
    }

    @Override
    public void UpdateDeviceStatus(String deviceCode, boolean state) {
        int v = state?1:-1;
        this.deviceMapper.udpateDeviceState(deviceCode,v);
        this.UpdateRealTimeInspect(deviceCode,DeviceStateFiled.OnlineState,true,3);
    }

    @Override
    public void UpdateRealTimeInspect(String deviceCode, DeviceStateFiled filed, Object value,int fieldtype) {
        TramDeviceInfo deviceInfo = this.getDeviceInfoByCode(deviceCode);
        int count = this.deviceMapper.getRealtimeInspectCount(deviceCode);
        if(count == 0){
            TramDeviceStateInspectRealtimeInfo info = new TramDeviceStateInspectRealtimeInfo();
            info.setDeviceid(deviceInfo.getId());
            info.setDeviceCode(deviceInfo.getDevicecode());
            this.deviceMapper.insertRealtimeInspect(info);
        }
        this.deviceMapper.updateRealtimeInspect(deviceCode,filed.getValue(),value,fieldtype);
    }

    @Override
    public List<DeviceViewModel> getList(long userId, int devicetype, long departmentId, long lineId, int status, String keywords) {

        List<Long> departments = this.iDepartmentService.GetDepartmentIdsByUserId(userId);
        return this.deviceMapper.getBusList(departments,devicetype,departmentId,lineId,status,keywords);
    }

    @Override
    public BusAndDeviceViewModel getDeviceViewModel(long id) {
        BusAndDeviceViewModel deviceViewModel = this.deviceMapper.getBusAndDeviceInfo(id);
        List<ChannelViewModel> list = this.deviceMapper.getChannelViewModelList(id);
        deviceViewModel.setChannellist(JSON.toJSONString(list));
        return deviceViewModel;
    }

    @Override
    public List<ComboTreeModel> getDriverComboList(long departmentid) {
        return this.driverMapper.getDriverComboList(departmentid);
    }

    @Override
    public void saveDeviceInfo(BusAndDeviceViewModel model) {
        TramDeviceInfo deviceInfo = model.ConvertToDeviceInfo();
        TramBusInfo busInfo = model.ConvertToBusInfo();
        if(model.getId() == 0)
        {
            this.deviceMapper.insertBusInfo(busInfo);
            long busId = busInfo.getId();
            model.setBusid(busId);
            deviceInfo.setBusid(busId);
            this.deviceMapper.insertDeviceInfo(deviceInfo);
        }else{
            this.deviceMapper.updateBusInfo(busInfo);
            this.deviceMapper.updateDeviceInfo(deviceInfo);
        }
        this.deviceMapper.removeChannelInfo(deviceInfo.getId());
        List<ChannelViewModel> cList = JSON.parseArray(model.getChannellist(),ChannelViewModel.class);
        for(ChannelViewModel m:cList){
            TramChannelInfo channelInfo = new TramChannelInfo();
            channelInfo.setDeviceid(deviceInfo.getId());
            channelInfo.setChannelname(m.getChannelname());
            channelInfo.setNo(m.getNo());
            channelInfo.setSupportptz(m.isSupportptz());
            this.deviceMapper.insertChannelInfo(channelInfo);
        }
    }

    @Override
    public List<Long> getBusIdsByDeviceIds(String deviceIds) {
        return this.deviceMapper.getBusIds(deviceIds);
    }

    @Override
    public void removeDeviceInfo(String deviceIds) {
        List<Long> busIds = this.deviceMapper.getBusIds(deviceIds);
        this.deviceMapper.removeDeviceInfo(deviceIds);
        this.deviceMapper.removeBusInfo(StringHelper.listToString(busIds,','));
        this.deviceMapper.removeChannels(deviceIds);
    }

    @Override
    public void insertRadarInfo(TramRadarInfo radarInfo) {
        this.mongoTemplate.save(radarInfo);
    }

    @Override
    public void insertDeviceStatusInfo(TramDeviceStatusInfo deviceStatusInfo) {
        this.mongoTemplate.save(deviceStatusInfo);
    }

    @Override
    public void updateDeviceNvrStatus(TramDeviceInfo deviceInfo, HVNVRModel hvnvrModel) {
        switch (hvnvrModel.getType()){
            case 1://录像状态
                //true:主盘 false:副盘
                boolean diskType = Boolean.valueOf(hvnvrModel.getValue1());
                //状态
                boolean videotapState = Boolean.valueOf(hvnvrModel.getValue2());
                this.UpdateRealTimeInspect(hvnvrModel.getCode(),DeviceStateFiled.Videotape,videotapState,3);
                if(!videotapState)
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(),deviceInfo.getId(),
                            hvnvrModel.getUpdateTime(),1001,"录像状态异常:"+(diskType?"主盘":"副盘"),"",""));
                break;
            case 2://视频状态
                //状态
                boolean videoState = Boolean.valueOf(hvnvrModel.getValue1());
                this.UpdateRealTimeInspect(hvnvrModel.getCode(),DeviceStateFiled.Video,videoState,3);
                if(!videoState)
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(),deviceInfo.getId(),
                            hvnvrModel.getUpdateTime(),1002,"视频状态异常","",""));
                break;
            case 3://硬盘状态
                boolean diskState = Boolean.valueOf(hvnvrModel.getValue1());
                this.UpdateRealTimeInspect(hvnvrModel.getCode(),DeviceStateFiled.HardDisk,diskState,3);
                if(!diskState)
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(),deviceInfo.getId(),
                            hvnvrModel.getUpdateTime(),1003,"硬盘状态异常","",""));
                break;
            case 4://SdCard状态
                boolean sdcardState = Boolean.valueOf(hvnvrModel.getValue1());
                this.UpdateRealTimeInspect(hvnvrModel.getCode(),DeviceStateFiled.SDCard,sdcardState,3);
                if(!sdcardState)
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(),deviceInfo.getId(),
                            hvnvrModel.getUpdateTime(),1004,"SD卡状态异常","",""));
                break;
            case 5://硬盘空间
                String surplusDiskSize = hvnvrModel.getValue2();//剩余空间
                this.UpdateRealTimeInspect(hvnvrModel.getCode(),DeviceStateFiled.SurplusDiskSize,surplusDiskSize,1);
                TramBasicInfo basicInfo = this.iBasicService.getBasicInfoByCustomId(1003);
                Double thrshold = Double.valueOf(basicInfo.getThreShold());
                if(Double.valueOf(surplusDiskSize)<=thrshold)
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(),deviceInfo.getId(),
                            hvnvrModel.getUpdateTime(),1003,"硬盘剩余空间不足"+thrshold+"G","",""));
                break;
            case 6://SD卡空间
                String surplusSdcardSize = hvnvrModel.getValue2();//剩余空间
                this.UpdateRealTimeInspect(hvnvrModel.getCode(),DeviceStateFiled.SurplusSdcardSize,surplusSdcardSize,1);
                TramBasicInfo basicInfo2 = this.iBasicService.getBasicInfoByCustomId(1004);
                Double thrshold2 = Double.valueOf(basicInfo2.getThreShold());
                if(Double.valueOf(surplusSdcardSize)<=thrshold2)
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(),deviceInfo.getId(),
                            hvnvrModel.getUpdateTime(),1003,"SD卡剩余空间不足"+thrshold2+"G","",""));
                break;
            case 7://时间校准
                Boolean timingState = Boolean.valueOf(hvnvrModel.getValue1());
                this.UpdateRealTimeInspect(hvnvrModel.getCode(),DeviceStateFiled.TimingState,timingState,3);
                break;
            case 8://CPU使用率
                Double cpuUseRate = Double.valueOf(hvnvrModel.getValue2());
                this.UpdateRealTimeInspect(hvnvrModel.getCode(),DeviceStateFiled.CPUUseRate,cpuUseRate,2);
                TramBasicInfo basic = this.iBasicService.getBasicInfoByCustomId(1005);
                Double cpuUseRateThrshold = Double.valueOf(basic.getThreShold());
                if(cpuUseRate>=cpuUseRateThrshold)
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(),deviceInfo.getId(),
                            hvnvrModel.getUpdateTime(),1005,"CPU使用率过高","",""));
                break;
            case 9:
                Double cpuTemp = Double.valueOf(hvnvrModel.getValue2());
                this.UpdateRealTimeInspect(hvnvrModel.getCode(),DeviceStateFiled.CPUTemp,cpuTemp,2);
                TramBasicInfo basic1 = this.iBasicService.getBasicInfoByCustomId(1006);
                Double cpuTempThrshold = Double.valueOf(basic1.getThreShold());
                if(cpuTemp>=cpuTempThrshold)
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(),deviceInfo.getId(),
                            hvnvrModel.getUpdateTime(),1006,"CPU温度过高","",""));
                break;
            case 10:
                Double memoryTemp = Double.valueOf(hvnvrModel.getValue2());
                this.UpdateRealTimeInspect(hvnvrModel.getCode(),DeviceStateFiled.MemoryUseRate,memoryTemp,2);
                TramBasicInfo basic2 = this.iBasicService.getBasicInfoByCustomId(1007);
                Double memoryThrshold = Double.valueOf(basic2.getThreShold());
                if(memoryTemp>=memoryThrshold)
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(),deviceInfo.getId(),
                            hvnvrModel.getUpdateTime(),1007,"内存使用率过高","",""));
                break;
            case 11:
                Double diskTemp = Double.valueOf(hvnvrModel.getValue2());
                this.UpdateRealTimeInspect(hvnvrModel.getCode(),DeviceStateFiled.DiskTemp,diskTemp,2);
                TramBasicInfo basic3 = this.iBasicService.getBasicInfoByCustomId(1008);
                Double diskTemphrshold = Double.valueOf(basic3.getThreShold());
                if(diskTemp>=diskTemphrshold)
                    this.iCanService.AddAlarmInfo(this.iCanService.getAlarmQuery(deviceInfo.getDevicecode(),deviceInfo.getId(),
                            hvnvrModel.getUpdateTime(),1008,"硬盘温度过高","",""));
                break;

        }
    }

    @Override
    public TramDeviceStateInspectRealtimeInfo getDeviceStateInspectRealTimeInfo(long deviceid) {
        return this.deviceMapper.getDeviceStateInspectRealTimeInfo(deviceid);
    }

    @Override
    public TramDispatchInfo getLastDispatchInfo(long deviceid) {
        Query query = new Query();
        query.addCriteria(new Criteria("deviceid").is(deviceid));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));
        return this.mongoTemplate.findOne(query,TramDispatchInfo.class);
    }

    @Override
    public List<MapDeviceViewModel> getMapDeviceList(String devices) {
        return this.deviceMapper.getMapDeviceList(devices);
    }

    @Override
    public DeviceViewModel getDeviceConfig(long id) {
        return this.deviceMapper.getDeviceViewModel(id);
    }

    @Override
    public List<DeviceInspectViewModel> getDeviceInspectList(long userid, long departmentid, long lineid, int type, String keywords) {
        List<Long> departmens = this.iDepartmentService.GetDepartmentIdsByUserId(userid);
        return this.deviceMapper.getDeviceInspectList(departmens,departmentid,lineid,type,keywords);
    }

    @Override
    public void autoCanSignleStatis() {
        List<TramDeviceInfo> devices = this.deviceMapper.getAllDevices();
        for(TramDeviceInfo device:devices){
            Query query1 = new Query();
            String lastTime = DateUtil.getCurrentTime(DateStyle.YYYY_MM_DD);
            String firstTime = DateUtil.addDay(DateUtil.getCurrentTime(DateStyle.YYYY_MM_DD),-1);
            query1.addCriteria(new Criteria("devicecode").is(device.getDevicecode()));
            query1.addCriteria(new Criteria("updatetime").lte(lastTime));
            query1.with(new Sort( new Sort.Order(Sort.Direction.DESC,"updatetime")));
            TramCanInfo lastCanInfo = this.mongoTemplate.findOne(query1,TramCanInfo.class);
            Query query2 = new Query();
            query2.addCriteria(new Criteria("devicecode").is(device.getDevicecode()));
            query2.addCriteria(new Criteria("updatetime").gte(firstTime));
            query2.with(new Sort( new Sort.Order(Sort.Direction.ASC,"updatetime")));
            TramCanInfo firstCanInfo = this.mongoTemplate.findOne(query2,TramCanInfo.class);
            if(firstCanInfo != null && lastCanInfo != null){
                TramBusInfo busInfo = this.deviceMapper.getBusInfo(device.getBusid());
                String updateTime = DateUtil.getCurrentTime(DateStyle.YYYY_MM_DD);
                CanHistoryEveryDayInfo everyDayInfo = new CanHistoryEveryDayInfo();
                everyDayInfo.setUpdatetime(updateTime);
                Double mileage = Double.valueOf(lastCanInfo.getTotalmileage())-Double.valueOf(firstCanInfo.getTotalmileage());
                everyDayInfo.setTotalmileage(mileage);
                double avg =0D;
                if(!StringHelper.isEmpty(lastCanInfo.getBaterysoc())&&!StringHelper.isEmpty(firstCanInfo.getBaterysoc())){
                    avg = Double.valueOf(firstCanInfo.getBaterysoc())-Double.valueOf(lastCanInfo.getBaterysoc());
                }
                if(busInfo.getBustype()== BusType.DoubleBMSBus.getValue()||busInfo.getBustype()==BusType.PureElectricBus.getValue()){
                    //电车
                    everyDayInfo.setElectricavg(avg);
                }
                else{
                    everyDayInfo.setGasavg(avg);
                }
                everyDayInfo.setGasonlieavg(0D);
                everyDayInfo.setDeviceid(device.getId());
                everyDayInfo.setFaultonelv(this.alarmMapper.getCountByDeviceAndLevel(device.getId(),1));
                everyDayInfo.setFaultsecondlv(this.alarmMapper.getCountByDeviceAndLevel(device.getId(),2));
                everyDayInfo.setFaultthreelv(this.alarmMapper.getCountByDeviceAndLevel(device.getId(),3));
                long unsafeNum = this.deviceMapper.getUnsafeCountByDeviceIdEveryDay(device.getId(),firstTime,lastTime);
                everyDayInfo.setUnsafenumber(unsafeNum);
            }
        }
    }

    @Override
    public void autoDeviceStatus(){
        List<TramDeviceInfo> devices = this.deviceMapper.getAllDevices();
        for(TramDeviceInfo device:devices){
            String nowTime = DateUtil.getCurrentTime();
            String stTime = DateUtil.addMinute(nowTime,-1);
            long deviceHealthCount = this.getDeviceHealthInfo(device.getDevicecode(),stTime,nowTime);
            if(deviceHealthCount == 0){
                this.UpdateRealTimeInspect(device.getDevicecode(),DeviceStateFiled.OnlineState,false,3);
            }else{
                this.UpdateRealTimeInspect(device.getDevicecode(),DeviceStateFiled.OnlineState,true,3);
            }
            //推送
            PushModel pushModel = new PushModel();
            pushModel.setType(1);
            pushModel.setMsgInfo(this.getCurrentDeviceStatus(device.getId()));
            this.iPushService.sendMsg(pushModel);
        }
    }

    @Override
    public DeviceStatusPushModel getCurrentDeviceStatus(long deviceId){
        TramDeviceInfo deviceInfo = this.getDeviceInfoById(deviceId);
        DeviceStatusPushModel pushModel = new DeviceStatusPushModel();
        pushModel.setCode(deviceInfo.getDevicecode());
        TramDeviceStateInspectRealtimeInfo inspectInfo = this.getDeviceStateInspectRealTimeInfo(deviceId);
        pushModel.setCanState(inspectInfo.isCanstate());
        pushModel.setGpsState(inspectInfo.isGpsstate());
        pushModel.setHostSoftType(deviceInfo.getHostsofttype().intValue());
        pushModel.setOnline(inspectInfo.isOnlineState());
        return pushModel;
    }

    @Override
    public long getDeviceHealthInfo(String deviceCode, String starttime, String endTime) {
        Query query = new Query();
        query.addCriteria(new Criteria("devicecode").is(deviceCode));
        query.addCriteria(new Criteria("updatetime").lte(endTime));
        query.addCriteria(new Criteria("updatetime").gte(starttime));
        return this.mongoTemplate.count(query,TramDeviceHealthInfo.class);
    }


}
