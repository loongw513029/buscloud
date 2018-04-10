package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.DateStyle;
import com.sztvis.buscloud.core.DateUtil;
import com.sztvis.buscloud.model.domain.TramCanInfo;
import com.sztvis.buscloud.model.domain.TramDeviceStateInspectRealtimeInfo;
import com.sztvis.buscloud.model.domain.TramDispatchInfo;
import com.sztvis.buscloud.model.domain.TramGpsInfo;
import com.sztvis.buscloud.model.dto.BusAndDeviceViewModel;
import com.sztvis.buscloud.model.dto.GpsViewModel;
import com.sztvis.buscloud.model.dto.MapHistoryLocationModel;
import com.sztvis.buscloud.model.entity.DeviceStateFiled;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.SpringDataPageable;
import com.sztvis.buscloud.service.ICanService;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IGpsService;
import com.sztvis.buscloud.util.GPSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/12 上午9:10
 */
@Service
public class GpsService implements IGpsService{

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private IDeviceService iDeviceService;
    @Autowired
    private IGpsService iGpsService;
    @Autowired
    private ICanService iCanService;
    @Override
    public void saveGpsInfo(TramGpsInfo gpsInfo) {
        this.mongoTemplate.save(gpsInfo);
        //保存数据，跟新设备Gps状态
        this.iDeviceService.UpdateRealTimeInspect(gpsInfo.getDevicecode(), DeviceStateFiled.GpsState,true,3);
    }

    @Override
    public TramGpsInfo getLastGpsInfo(String deviceCode,String UpdateTime) {
        Query query1=new Query();
        query1.addCriteria(new Criteria("devicecode").is(deviceCode));
        query1.addCriteria(new Criteria("updatetime").lte(UpdateTime));//<=
        query1.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));
        TramGpsInfo gpsInfo = this.mongoTemplate.findOne(query1,TramGpsInfo.class);
        return gpsInfo;
    }

    @Override
    public TramGpsInfo getLastGpsInfo(String deviceCode) {
        Query query1=new Query();
        query1.addCriteria(new Criteria("devicecode").is(deviceCode));
        query1.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));
        TramGpsInfo gpsInfo = this.mongoTemplate.findOne(query1,TramGpsInfo.class);
        return gpsInfo;
    }

    @Override
    public TramGpsInfo getLastGpsInfo(long deviceId) {
        Query query1=new Query();
        query1.addCriteria(new Criteria("deviceid").is(deviceId));
        query1.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));
        TramGpsInfo gpsInfo = this.mongoTemplate.findOne(query1,TramGpsInfo.class);
        return gpsInfo;
    }

    @Override
    public GpsViewModel getAppGpsViewModel(long deviceid,String starttime) {
        TramGpsInfo gpsInfo = this.iGpsService.getLastGpsInfo(deviceid);
        BusAndDeviceViewModel deviceInfo = this.iDeviceService.getDeviceViewModel(deviceid);
        TramCanInfo canInfo = this.iCanService.getLastCanInfo(deviceInfo.getDevicecode());
        TramDeviceStateInspectRealtimeInfo real = this.iDeviceService.getDeviceStateInspectRealTimeInfo(deviceid);
        GpsViewModel model = new GpsViewModel();
        model.setDeviceId(deviceid);
        model.setCode(gpsInfo.getDevicecode());
        model.setUpdateTime(gpsInfo.getUpdatetime());
        model.setGpsOnLine(real.isGpsstate());
        model.setSpeed(canInfo==null?gpsInfo==null?"0":gpsInfo.getSpeed()+"":canInfo.getSpeed());
        model.setClientIp(deviceInfo.getClientip());
        model.setOnline(real.isOnlineState());
        model.setChannel(deviceInfo.getVideochannel());
        model.setDeviceNumber(deviceInfo.getBusnumber());
        model.setLocation(gpsInfo==null?"":(gpsInfo.getLongitude()+","+gpsInfo.getLatitude()));
        int state = this.getDeviceCurrentGpsState(deviceid,starttime,real.isGpsstate(),real.isOnlineState(),deviceInfo.getDevicecode());
        model.setState(state);
        model.setRotate(gpsInfo==null?0:gpsInfo.getDirection());
        model.setUpTime(gpsInfo.getUpdatetime());
        TramDispatchInfo dispatchInfo = this.iDeviceService.getLastDispatchInfo(deviceid);
        model.setDispatch(dispatchInfo==null?"":dispatchInfo.getDispatchname());
        return model;
    }

    @Override
    public long getGpsCount(long deviceid, String starttime, String endtime) {
        Query query1=new Query();
        query1.addCriteria(new Criteria("deviceid").is(deviceid));
        query1.addCriteria(new Criteria().andOperator(Criteria.where("updatetime").lte(endtime),
                Criteria.where("updatetime").lte(starttime)));
        return this.mongoTemplate.count(query1,TramGpsInfo.class);
    }

    @Override
    public int getDeviceCurrentGpsState(long deviceid, String starttime, boolean gpsState,boolean onLineState,String deviceCode) {
        long gpsCount = this.getGpsCount(deviceid,starttime, DateUtil.getCurrentTime());
        boolean isPark = this.iCanService.IsPark(deviceCode);
        if(!onLineState)
            return 7;
        if(gpsState){
            if(!isPark){
                if(gpsCount == 0)
                    return 1;
                else
                    return 3;
            }else{
                if(gpsCount == 0)
                    return 2;
                else
                    return 4;
            }
        }else{
            if(gpsCount == 0)
                return 5;
            else
                return 6;
        }
    }

    @Override
    public PageBean<MapHistoryLocationModel> getMapHistoryGpsList(long deviceId, String startTime, String endTime, int page, int rows) {
        SpringDataPageable pageable = new SpringDataPageable();
        Query query = new Query();
        query.addCriteria(new Criteria("deviceid").is(deviceId));
        query.addCriteria(new Criteria().andOperator(Criteria.where("updatetime").lte(endTime),
                Criteria.where("updatetime").lte(startTime)));
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime"));
        pageable.setPagenumber(page);
        pageable.setPagesize(rows);
        pageable.setSort(sort);
        Long count = mongoTemplate.count(query, TramGpsInfo.class);
        List<TramGpsInfo> list = mongoTemplate.find(query.with(pageable), TramGpsInfo.class);
        Page<TramGpsInfo> pagelist = new PageImpl<TramGpsInfo>(list, pageable, count);
        PageBean<MapHistoryLocationModel> pageBean = new PageBean<>(page, rows, count.intValue());
        List<MapHistoryLocationModel> list2 = new ArrayList<>();
        for (TramGpsInfo gps:pagelist.getContent()) {
            MapHistoryLocationModel model = new MapHistoryLocationModel();
            model.setLatitude(gps.getLatitude());
            model.setLongitude(gps.getLongitude());
            model.setUpdateTime(gps.getUpdatetime());
            model.setSpeed(gps.getSpeed());
            list2.add(model);
        }
        pageBean.setItems(list2);
        return pageBean;
    }

    @Override
    public List<String> getLocations(long deviceId, String startTime, String endTime) {
        Query query = new Query();
        query.addCriteria(new Criteria("deviceid").is(deviceId));
        query.addCriteria(new Criteria().andOperator(Criteria.where("updatetime").lte(endTime),
                Criteria.where("updatetime").lte(startTime)));
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime"));
        query.with(sort);
        List<TramGpsInfo> list = this.mongoTemplate.find(query,TramGpsInfo.class);
        List<String> list2 = new ArrayList<>();
        for (TramGpsInfo g:list) {
            double[] d = GPSUtil.gps84_To_Gcj02(Double.valueOf(g.getLatitude()),Double.valueOf(g.getLongitude()));
            list2.add(d[1]+","+d[0]);
        }
        return list2;
    }

    @Override
    public long GetCountBy10sGps(long deviceId, String date, int second)
    {
        String starttime=DateUtil.addSecond(date,-second);
        Query query1=new Query();
        query1.addCriteria(Criteria.where("deviceid").is(deviceId));
        query1.addCriteria(Criteria.where("updatetime").lte(date).gte(starttime));
        return this.mongoTemplate.count(query1,TramGpsInfo.class);
    }
}
