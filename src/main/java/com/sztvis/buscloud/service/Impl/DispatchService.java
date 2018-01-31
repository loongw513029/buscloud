package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.model.domain.TramCanInfo;
import com.sztvis.buscloud.model.domain.TramDispatchInfo;
import com.sztvis.buscloud.service.IDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/25 上午9:15
 */
@Service
public class DispatchService implements IDispatchService{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void insertDispatchInfo(TramDispatchInfo dispatchInfo) {
        this.mongoTemplate.save(dispatchInfo);
    }

    @Override
    public TramDispatchInfo getLastDispatchInfo(long deviceId) {
        Query query = new Query();
        query.addCriteria(new Criteria("deviceid").is(deviceId));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));
        return this.mongoTemplate.findOne(query,TramDispatchInfo.class);
    }

}
