package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.model.domain.TramGpsInfo;
import com.sztvis.buscloud.model.entity.DeviceStateFiled;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IGpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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

    @Override
    public void saveGpsInfo(TramGpsInfo gpsInfo) {
        this.mongoTemplate.save(gpsInfo);
        //保存数据，跟新设备Gps状态
        this.iDeviceService.UpdateRealTimeInspect(gpsInfo.getDevicecode(), DeviceStateFiled.GpsState,true);
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
}
