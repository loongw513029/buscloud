package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.DateUtil;
import com.sztvis.buscloud.core.TramException;
import com.sztvis.buscloud.mapper.AlarmMapper;
import com.sztvis.buscloud.mapper.BasicMapper;
import com.sztvis.buscloud.mapper.CanMapper;
import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.model.domain.*;
import com.sztvis.buscloud.model.dto.*;
import com.sztvis.buscloud.model.dto.service.SaveAlarmQuery;
import com.sztvis.buscloud.service.ICanService;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IGpsService;
import com.sztvis.buscloud.util.DayTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
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
    @Autowired
    private CanMapper canMapper;
    @Autowired
    private IDeviceService iDeviceService;

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
        update.set("electricCanInfo.idlingSwitch",ecInfo.getIdlingSwitch());
        update.set("electricCanInfo.minimumBatteryNumber",ecInfo.getMinimumBatteryNumber());
        update.set("electricCanInfo.allBatteryMinimum",ecInfo.getAllBatteryMinimum());
        update.set("electricCanInfo.maxmumBatteryNumber",ecInfo.getMaxmumBatteryNumber());
        update.set("electricCanInfo.allBatteryMax",ecInfo.getAllBatteryMax());
        update.set("electricCanInfo.minimumTempBatteryNumber",ecInfo.getMinimumTempBatteryNumber());
        update.set("electricCanInfo.minimumTemp",ecInfo.getMinimumTemp());
        update.set("electricCanInfo.maxTempBatteryNumber",ecInfo.getMaxTempBatteryNumber());
        update.set("electricCanInfo.maxTemp",ecInfo.getMaxTemp());
        update.set("electricCanInfo.chargeSurplusHour",ecInfo.getChargeSurplusHour());
        update.set("electricCanInfo.chargeSurplusMinute",ecInfo.getChargeSurplusMinute());
        update.set("electricCanInfo.signleChargeValue",ecInfo.getSignleChargeValue());
        update.set("electricCanInfo.signleDisChargeValue",ecInfo.getSignleDisChargeValue());
        update.set("electricCanInfo.batteryTempState",ecInfo.getBatteryTempState());
        update.set("electricCanInfo.leakageState",ecInfo.getLeakageState());
        update.set("electricCanInfo.mxElec",ecInfo.getMxElec());
        update.set("electricCanInfo.setTemp",ecInfo.getSetTemp());
        update.set("electricCanInfo.airState",ecInfo.getAirState());
        update.set("electricCanInfo.okState",ecInfo.getOkState());
        update.set("electricCanInfo.shieldTurnState",ecInfo.getShieldTurnState());
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
            alarmInfo.setDepartmentId(deviceInfo.getDepartmentid());
            alarmInfo.setState(basicInfo.isEnable()?1:0);
            alarmInfo.setPath("");
            if(gpsInfo!=null)
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
    @Override
    public TramCanInfo getLastCanInfo(String deviceCode,String updateTime) {
        Query query = new Query();
        query.addCriteria(new Criteria("devicecode").is(deviceCode));
        query.addCriteria(new Criteria("updatetime").lte(updateTime));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));
        return this.mongoTemplate.findOne(query,TramCanInfo.class);
    }
    public SaveAlarmQuery getAlarmQuery(String deviceCode,long deviceId,String updateTime,int type,String value,String extras,String path){
        SaveAlarmQuery query = new SaveAlarmQuery();
        String[] extraArray = extras.split(",");
        TramGpsInfo gpsInfo = this.iGpsService.getLastGpsInfo(deviceCode,updateTime);
        if(extraArray ==null||extraArray.length ==0){
            query.setSpeed(0D);
            query.setDistance(0L);
            query.setBrake(false);
        }else{
            query.setSpeed(Double.valueOf(extraArray[0]));
            query.setDistance(Double.valueOf(extraArray[1]));
            query.setBrake(extraArray[2]=="2");
        }
        query.setAlarmTime(updateTime);
        query.setAlarmType(type);
        query.setDeviceCode(deviceCode);
        query.setPath(path);
        query.setDeviceId(deviceId);
        query.setValue(value);
        return query;
    }

    @Override
    public CanViewModel getLastCanViewModel(String devicecode) {
        CanViewModel viewModel = new CanViewModel();
        TramCanInfo canInfo = this.getLastCanInfo(devicecode);
        TramGpsInfo gpsInfo = this.iGpsService.getLastGpsInfo(devicecode);
        CanModel canModel = new CanModel();
        canModel.setAfterpressure(canInfo.getPressure2());
        canModel.setBeforepressure(canInfo.getPressure1());
        canModel.setBrakeopenings("1");
        canModel.setElec(canInfo.getElectricCanInfo());
        canModel.setElecconsumption("");
        canModel.setElectricalratespeed("0");
        canModel.setElectricalstat("2");
        canModel.setLeftpress(canInfo.getPressure1());
        canModel.setRightpress(canInfo.getPressure2());
        canModel.setBeforepressure(canInfo.getPressure1());
        canModel.setAfterpressure(canInfo.getPressure2());
        canModel.setSpeed(canInfo.getSpeed());
        canModel.setIncartemp(canInfo.getIncartemperature());
        canModel.setOutcartemp(canInfo.getOutcartemperature());
        canModel.setWatertemp(canInfo.getWatertemperature());
        canModel.setTotalmileage(canInfo.getTotalmileage());
        canModel.setShortmileage(canInfo.getShortmileage());
        canModel.setSoc(canInfo.getBaterysoc());
        canModel.setRatespeed(canInfo.getRotationalspeed());
        canModel.setRatespped2(canInfo.getElectricCanInfo().getRightElecRote());
        canModel.setRoate(gpsInfo==null?"0":gpsInfo.getDirection().toString());
        canModel.setLocation(gpsInfo==null?"":gpsInfo.getLongitude()+","+gpsInfo.getLatitude());
        canModel.setOilopenings(canInfo.getElectricCanInfo().getAcceleratorPedal());
        CanStatModel s = new CanStatModel();
        s.setAbsturn(this.getCanStat(canInfo.getActs(),250));
        s.setAccturn(this.getCanStat(canInfo.getActs(),59));
        s.setActurn(this.getCanStat(canInfo.getActs(),258));
        s.setAfterfoglampsturn(this.getCanStat(canInfo.getActs(),48));
        s.setBeforefoglampsturn(this.getCanStat(canInfo.getActs(),47));
        s.setEnginepreheatingturn(this.getCanStat(canInfo.getActs(),70));
        s.setDippedlightsturn(this.getCanStat(canInfo.getActs(),50));
        s.setEngineworkturn(this.getCanStat(canInfo.getActs(),70));
        s.setFootbraketurn(this.getCanStat(canInfo.getActs(),42));
        s.setParkingbraketurn(this.getCanStat(canInfo.getActs(),43));
        s.setHeaderdoorturn(this.getCanStat(canInfo.getActs(),39));
        s.setMiddledoorturn(this.getCanStat(canInfo.getActs(),40));
        s.setLastdoorturn(this.getCanStat(canInfo.getActs(),41));
        s.setHighbeamturn(this.getCanStat(canInfo.getActs(),49));
        s.setLeftturn(this.getCanStat(canInfo.getActs(),52));
        s.setRightturn(this.getCanStat(canInfo.getActs(),51));
        s.setSafetybelt(this.getCanStat(canInfo.getActs(),59));
        viewModel.setCanstatinfo(s);
        viewModel.setCaninfo(canModel);
        viewModel.setTime(canInfo.getUpdatetime());
        viewModel.setDispatchinfo(this.getLastDispathModel(canInfo.getDevicecode()));
        return viewModel;
    }

    @Override
    public DispatchModel getLastDispathModel(String devicecode) {
        Query query = new Query();
        query.addCriteria(new Criteria("devicecode").is(devicecode));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));
        TramDispatchInfo dispatchInfo = this.mongoTemplate.findOne(query,TramDispatchInfo.class);
        DispatchModel dModel = new DispatchModel();
        if(dispatchInfo!=null){
            dModel.setCurrentsite(dispatchInfo.getDispatchname());
            dModel.setInoroutsite(dispatchInfo.getDrivingdirection().intValue());
            dModel.setInoroutsitetype(dispatchInfo.getDispatchtype().intValue());
            dModel.setNextsite("");
            dModel.setSitetype(0);
        }
        return dModel;
    }

    @Override
    public void autoCalcUnsafeData(long deviceId, String updateTime) {

        //1.起步不关车门，车速从0递增到5km/h时，车门未关，持续1s

    }

    @Override
    public void insertUnsafeData(TramUnsafeBehaviorInfo behaviorInfo) {
        this.canMapper.insertUnsafeInfo(behaviorInfo);
    }

    @Override
    public void insertUnSafeData(long deviceId, String updateTime, int unsafeType) {
        TramDeviceInfo deviceInfo = this.deviceMapper.getDeviceInfoById(deviceId);
        TramGpsInfo gpsInfo = this.iGpsService.getLastGpsInfo(deviceInfo.getDevicecode(),updateTime);
        TramCanInfo canInfo = this.getLastCanInfo(deviceInfo.getDevicecode(),updateTime);
        TramUnsafeBehaviorInfo behaviorInfo = new TramUnsafeBehaviorInfo();
        behaviorInfo.setApplytime(Timestamp.valueOf(updateTime));
        behaviorInfo.setCreatetime(Timestamp.valueOf(DateUtil.getCurrentTime()));
        behaviorInfo.setDevicecode(deviceInfo.getDevicecode());
        behaviorInfo.setDeviceid(deviceId);
        behaviorInfo.setLocation(gpsInfo==null?"":gpsInfo.getLongitude()+","+gpsInfo.getLatitude());
        behaviorInfo.setRatespeed(canInfo==null?0:Long.valueOf(canInfo.getRotationalspeed()));
        behaviorInfo.setSpeed(canInfo==null?(gpsInfo==null?0:gpsInfo.getSpeed()):Double.valueOf(canInfo.getSpeed()));
        behaviorInfo.setUnsafetype((long)unsafeType);
        this.insertUnsafeData(behaviorInfo);
    }

    @Override
    public CanHistoryViewModel getCanHistorys(int dayType, long lineId) {
        CanHistoryViewModel viewModel = new CanHistoryViewModel();
        DayTypes dayTypes = new DayTypes().getDayByType(dayType);
        double totalMileage=0D,gasTotal=0D,gaslineTotal=0D,elecTotal=0D,longTime=0D;
        int f1 =0,f2 =0,f3=0,SpeedingTotal=0;
        List<CanHistoryEveryDayInfo> list = this.canMapper.getCanHistoryDayInfo(lineId,dayTypes.getStartTime(),dayTypes.getEndTime());
        for (CanHistoryEveryDayInfo c:list) {
            totalMileage+=c.getTotalmileage();
            gasTotal+=c.getGasavg();
            gaslineTotal+=c.getGasonlieavg();
            elecTotal+=c.getElectricavg();
            longTime+=c.getRuntimelong();
            SpeedingTotal+=c.getSpeedingtotal();
            f1+=c.getFaultonelv();
            f2+=c.getFaultsecondlv();
            f3+=c.getFaultthreelv();
        }
        viewModel.setLineId(lineId);
        viewModel.setElecEconomy(elecTotal);
        viewModel.setGasEconomy(gasTotal);
        viewModel.setTotalMileage(totalMileage);
        viewModel.setFuelEconomy(gaslineTotal);
        viewModel.setCarBusLongTime(longTime);
        viewModel.setSpeeding(SpeedingTotal);
        viewModel.setF1(f1);
        viewModel.setF2(f2);
        viewModel.setF3(f3);
        int day = 0;
        try {
            if(dayType<=4) {
                day = DateUtil.daysBetween(dayTypes.getStartTime(), dayTypes.getEndTime());
            }else{
                day = DateUtil.yearsBetween(dayTypes.getStartTime(),dayTypes.getEndTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<String> xalis = new ArrayList<>();
        List<List<Integer>> list1 = new ArrayList<>();
        List<String> faultXalias = new ArrayList<>();
        int p=0;
        for (TramBasicInfo b:this.basicMapper.getAlarmTypes(1)) {
            faultXalias.add(b.getAlarmName());
            List<Integer> list1s = new ArrayList<>();
            for(int i=0;i<day;i++){
                String start = DateUtil.addDay(dayTypes.getStartTime(),i),
                        end = DateUtil.addDay(start,1);
                if(dayType==5||dayType==6){
                    start = DateUtil.addMonth(dayTypes.getStartTime(),i);
                    end = DateUtil.addMonth(start,1);
                    if(p==0) {
                        xalis.add(start.split("-")[1] + "月");
                    }
                }else{
                    if(p==0) {
                        xalis.add(start.split("-")[2] + "日");
                    }
                }
                int count = this.canMapper.getAlarmTrendsCountByLineId(lineId,start,end,b.getId().intValue());
                list1s.add(count);
            }
            p++;
            list1.add(list1s);
        }
        viewModel.setXlias(xalis);
        viewModel.setFaultXalias(faultXalias);
        viewModel.setFaults(list1);
        List<List<Integer>> list2 = new ArrayList<>();
        List<String> unsafeXalias = new ArrayList<>();
        for (TramBasicInfo b:this.basicMapper.getAlarmTypes(78)) {
            unsafeXalias.add(b.getAlarmName());
            List<Integer> list2s = new ArrayList<>();
            for(int i=0;i<day;i++){
                String start = DateUtil.addDay(dayTypes.getStartTime(),i),
                        end = DateUtil.addDay(start,1);
                if(dayType==5||dayType==6){
                    start = DateUtil.addMonth(dayTypes.getStartTime(),i);
                    end = DateUtil.addMonth(start,1);
                }
                int count = this.canMapper.getAlarmTrendsCountByLineId(lineId,start,end,b.getId().intValue());
                list2s.add(count);
            }
            list2.add(list2s);
        }
        viewModel.setUnsafeXalias(unsafeXalias);
        viewModel.setFaults(list2);
        return viewModel;
    }

    @Override
    public List<List<Integer>> getAlarmTrends(int dayType, long lineId, List<Integer> types) {
        return null;
    }

    @Override
    public List<List<Integer>> getSignleAlarmTrends(int dayType, long deviceId, List<Integer> types) {
        return null;
    }



    private int getCanStat(List<TramCanActinfo> acts,int key){
        int r = 1;
        for(TramCanActinfo a:acts){
            if(a.getCustomId()==key){
                double d = Double.parseDouble(a.getValue());
                r = (int)d;
            }
        }
        return r;
    }
    @Override
    public boolean IsBarke(String deviceCode) {
        TramCanInfo canInfo = this.getLastCanInfo(deviceCode);
        boolean fag = false;
        for(TramCanActinfo c:canInfo.getActs()){
            if(c.getCustomId() == 42){
                fag = c.getValue() == "2";
                break;
            }
        }
        return fag;
    }

    @Override
    public boolean IsPark(String deviceCode) {
        TramCanInfo canInfo = this.getLastCanInfo(deviceCode);
        boolean fag = false;
        for(TramCanActinfo c:canInfo.getActs()){
            if(c.getCustomId() == 43){
                fag = c.getValue() == "2";
                break;
            }
        }
        return fag;
    }
}
