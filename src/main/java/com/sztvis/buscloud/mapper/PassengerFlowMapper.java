package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.TramPassengerFlow;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/3/29 上午11:21
 */
@Repository
public interface PassengerFlowMapper {

    @Insert("insert into TramPassengerFlow(deviceCode,deviceId,updateTime,type,klNumber1,klNumber2)values(#{deviceCode},#{deviceId},#{updateTime},#{type},#{klNumber1},#{klNumber2})")
    void insertPassengerFlow(TramPassengerFlow passengerFlow);
}
