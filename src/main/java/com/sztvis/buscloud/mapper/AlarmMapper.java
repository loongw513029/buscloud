package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.TramAlarmInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/11 下午3:30
 */
@Repository
public interface AlarmMapper {

    @Insert("insert into TramAlarmInfo(deviceId,deviceCode,updateTime,parentAlarmType,alarmType,value,speed,distance,isBrake,alarmVideoPath,location,state)values(#{deviceId},#{deviceCode},#{updateTime},#{parentAlarmType},#{alarmType},#{value},#{speed},#{distance},#{isBrake},#{alarmVideoPath},#{location},#{state})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void SaveAlarmInfo(TramAlarmInfo alarmInfo);
}
