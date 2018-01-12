package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.TramException;
import com.sztvis.buscloud.mapper.AlarmMapper;
import com.sztvis.buscloud.mapper.BasicMapper;
import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.model.domain.*;
import com.sztvis.buscloud.model.dto.service.SaveAlarmQuery;
import com.sztvis.buscloud.model.entity.DeviceStateFiled;
import com.sztvis.buscloud.service.ICanService;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IGpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/10 上午10:33
 */
@Service
public class CanService implements ICanService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private BasicMapper basicMapper;
    @Autowired
    private AlarmMapper alarmMapper;
    @Autowired
    private IGpsService iGpsService;

    @Override
    public TramCanInfo GetCanInfo(String deviceCode, String updateTime) {
        Query query = new Query();
        query.addCriteria(new Criteria("devicecode").is(deviceCode));
        query.addCriteria(new Criteria("updatetime").is(updateTime));
        return this.mongoTemplate.findOne(query,TramCanInfo.class);
    }

    @Override
    public void Save(TramCanInfo canInfo) {
        TramCanInfo obj = this.GetCanInfo(canInfo.getDevicecode(),canInfo.getUpdatetime());
        if(obj == null)
            this.mongoTemplate.save(canInfo);
        else
            this.Update(canInfo);
    }

    @Override
    public void Update(TramCanInfo canInfo) {
        Query query =new Query();
        query.addCriteria(new Criteria("devicecode").is(canInfo.getDevicecode()));
        query.addCriteria(new Criteria("updatetime").is(canInfo.getUpdatetime()));
        Update update = new Update();
        update.set("deviceid",canInfo.getDeviceid());
        update.set("devicecode",canInfo.getDevicecode());
        update.set("updatetime",canInfo.getUpdatetime());
        update.set("batteryvoltage",canInfo.getBatteryvoltage());
        update.set("batterycurrent",canInfo.getBatterycurrent());
        update.set("busstall",canInfo.getBusstall());
        update.set("baterysoc",canInfo.getBaterysoc());
        update.set("motorspeed",canInfo.getMotorspeed());
        update.set("enginefuelrae",canInfo.getEnginefuelrae());
        update.set("gasusetotal",canInfo.getGasusetotal());
        update.set("totalmileage",canInfo.getTotalmileage());
        update.set("shortmileage",canInfo.getShortmileage());
        update.set("fueleconomy",canInfo.getFueleconomy());
        update.set("fuelusespeed",canInfo.getFuelusespeed());
        update.set("oilpressure",canInfo.getOilpressure());
        update.set("pressure1",canInfo.getPressure1());
        update.set("pressure2",canInfo.getPressure2());
        update.set("remainingoil",canInfo.getRemainingoil());
        update.set("speed",canInfo.getSpeed());
        update.set("watertemperature",canInfo.getWatertemperature());
        update.set("rotationalspeed",canInfo.getRotationalspeed());
        update.set("totaloilconsumption",canInfo.getTotaloilconsumption());
        update.set("voltage",canInfo.getVoltage());
        update.set("tirelayoutnumber",canInfo.getTirelayoutnumber());
        update.set("tirenumber1",canInfo.getTirenumber1());
        update.set("tirepressure1",canInfo.getTirepressure1());
        update.set("tirepressure2",canInfo.getTirepressure2());
        update.set("tirenumber2",canInfo.getTirenumber2());
        update.set("outcartemperature",canInfo.getOutcartemperature());
        update.set("incartemperature",canInfo.getIncartemperature());
        ElectricCanInfo ecInfo = canInfo.getElectricCanInfo();
        if(ecInfo==null)
            ecInfo=new ElectricCanInfo();
        update.set("electricCanInfo.canId",ecInfo.getCanId());
        update.set("electricCanInfo.batterySingleCount",ecInfo.getBatterySingleCount());
        update.set("electricCanInfo.batteryProbeCount",ecInfo.getBatteryProbeCount());
        update.set("electricCanInfo.batteryMaxVoltageSingleCode",ecInfo.getBatteryMaxVoltageSingleCode());
        update.set("electricCanInfo.batteryMaxVoltage",ecInfo.getBatteryMaxVoltage());
        update.set("electricCanInfo.batteryMinVoltageCode",ecInfo.getBatteryMinVoltageCode());
        update.set("electricCanInfo.batteryMinVoltage",ecInfo.getBatteryMinVoltage());
        update.set("electricCanInfo.maxTempProbeNumber",ecInfo.getMaxTempProbeNumber());
        update.set("electricCanInfo.maxTempValue",ecInfo.getMaxTempValue());
        update.set("electricCanInfo.minTempProbeNumber",ecInfo.getMaxTempProbeNumber());
        update.set("electricCanInfo.minTempValue",ecInfo.getMinTempValue());
        update.set("electricCanInfo.highResistance",ecInfo.getHighResistance());
        update.set("electricCanInfo.electrical",ecInfo.getElectrical());
        update.set("electricCanInfo.leftElecRote",ecInfo.getLeftElecRote());
        update.set("electricCanInfo.leftElecInputVoltage",ecInfo.getLeftElecInputVoltage());
        update.set("electricCanInfo.leftElecTemp",ecInfo.getLeftElecTemp());
        update.set("electricCanInfo.leftElecContrTemp",ecInfo.getLeftElecContrTemp());
        update.set("electricCanInfo.leftElecTorque",ecInfo.getLeftElecTorque());
        update.set("electricCanInfo.leftElecMode",ecInfo.getLeftElecMode());
        update.set("electricCanInfo.leftElecCurrent",ecInfo.getLeftElecCurrent());
        update.set("electricCanInfo.rightElecRote",ecInfo.getRightElecRote());
        update.set("electricCanInfo.rightElecInputVoltage",ecInfo.getRightElecInputVoltage());
        update.set("electricCanInfo.rightElecTemp",ecInfo.getRightELecTemp());
        update.set("electricCanInfo.rightElecContrTemp",ecInfo.getRightElecContrTemp());
        update.set("electricCanInfo.rightElecTorque",ecInfo.getRightElecTorque());
        update.set("electricCanInfo.rightElecMode",ecInfo.getRightElecMode());
        update.set("electricCanInfo.rightElecCurrent",ecInfo.getRightElecCurrent());
        update.set("electricCanInfo.acceleratorPedal",ecInfo.getAcceleratorPedal());
        update.set("electricCanInfo.carVIN",ecInfo.getCarVIN());
        this.mongoTemplate.updateFirst(query, update, TramCanInfo.class);
    }

    @Override
    public TramCanInfo FindLastById(long deviceId) {
        Query query =new Query();
        query.addCriteria(new Criteria("deviceid").is(deviceId));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));
        return this.mongoTemplate.findOne(query,TramCanInfo.class);
    }

    @Override
    public TramCanInfo FindLastByCode(String deviceCode) {
        Query query =new Query();
        query.addCriteria(new Criteria("devicecode").is(deviceCode));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));
        return this.mongoTemplate.findOne(query,TramCanInfo.class);
    }

    @Override
    public void AddAlarmInfo(SaveAlarmQuery query) {
        TramDeviceInfo deviceInfo = this.deviceMapper.getDeviceInfoByCode(query.getDeviceCode());
        if(deviceInfo == null)
            throw new TramException("设备编码不存在！");
        int alarmType=query.getAlarmType();
        TramBasicInfo basicInfo = this.basicMapper.getBasicInfoByCustomId(alarmType);
        //这里加上一天之内已经存在了的报警，将不再增加

        //判断报警类型是否打开
        if(basicInfo.isTurn()){
            //获得该设备最后一条gps数据
            TramGpsInfo gpsInfo = this.iGpsService.getLastGpsInfo(query.getDeviceCode(),query.getAlarmTime());
            TramAlarmInfo alarmInfo = new TramAlarmInfo();
            alarmInfo.setDeviceId(deviceInfo.getId());
            alarmInfo.setDeviceCode(deviceInfo.getDevicecode());
            alarmInfo.setUpdateTime(Timestamp.valueOf(query.getAlarmTime()));
            alarmInfo.setAlarmType(query.getAlarmType());
            alarmInfo.setParentAlarmType(basicInfo.getParentId());
            alarmInfo.setValue(query.getValue());
            alarmInfo.setSystemInsertTime(new Timestamp(System.currentTimeMillis()));
            alarmInfo.setAlarmVideoPath(query.getPath());
            alarmInfo.setSpeed(query.getSpeed());
            alarmInfo.setDistance(query.getDistance());
            alarmInfo.setBrake(query.isBrake());
            alarmInfo.setState(basicInfo.isEnable()?1:0);
            alarmInfo.setLocation(gpsInfo.getLongitude()+","+gpsInfo.getLatitude());
            this.alarmMapper.SaveAlarmInfo(alarmInfo);
            //返回主键Id
            long alarmId = alarmInfo.getId();
            //是否需要推送（推送到App,推送到页面）
            if(basicInfo.isPush()){

            }
        }
    }

    @Override
    public void AddCanActInfo(String updateTime, String deviceCode, List<TramCanActinfo> map) {
        Query query=new Query();
        query.addCriteria(new Criteria("devicecode").is(deviceCode));
        query.addCriteria(new Criteria("updatetime").is(updateTime));
        //查找对应时间的Can信息
        TramCanInfo canInfo = this.mongoTemplate.findOne(query,TramCanInfo.class);
        if(canInfo != null){
            Update update = new Update();
            List<TramCanActinfo> list = canInfo.getActs();
            if(list==null)
                list = new ArrayList<>();
            Map<Integer,String> maps = new HashMap<>();
            for(TramCanActinfo ac:list){
                maps.put(ac.getCustomId(),ac.getValue());
            }
            for(TramCanActinfo ac:map){
                if(maps.keySet().contains(ac.getCustomId()))
                    list = UpdateActs(ac.getCustomId(),ac.getValue(),list);
                else
                    list.add(ac);
            }
            update.set("acts",list);
            this.mongoTemplate.updateFirst(query,update,TramCanInfo.class);
        }
    }
    private List<TramCanActinfo> UpdateActs(int customId,String newValue,List<TramCanActinfo> oldList){
        List<TramCanActinfo> list = new ArrayList<>();
        for(TramCanActinfo ac:oldList){
            if(ac.getCustomId()==customId){
                ac.setValue(newValue);
            }
            list.add(ac);
        }
        return list;
    }
    @Override
    public TramCanInfo getLastCanInfo(String deviceCode) {
        Query query = new Query();
        query.addCriteria(new Criteria("devicecode").is(deviceCode));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));
        return this.mongoTemplate.findOne(query,TramCanInfo.class);
    }
}
