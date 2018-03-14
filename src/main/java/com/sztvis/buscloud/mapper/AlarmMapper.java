package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.mapper.provider.AlarmProvider;
import com.sztvis.buscloud.model.domain.TramAlarmInfo;
import com.sztvis.buscloud.model.dto.AlarmViewModel;
import com.sztvis.buscloud.model.dto.HomeAlarmViewModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/11 下午3:30
 */
@Repository
public interface AlarmMapper {

    @Insert("insert into TramAlarmInfo(deviceId,deviceCode,departmentId,updateTime,parentAlarmType,alarmType,value,speed,distance,isBrake,alarmVideoPath,location,state)values(#{deviceId},#{deviceCode},#{departmentId},#{updateTime},#{parentAlarmType},#{alarmType},#{value},#{speed},#{distance},#{isBrake},#{alarmVideoPath},#{location},#{state})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void SaveAlarmInfo(TramAlarmInfo alarmInfo);

    @SelectProvider(type = AlarmProvider.class,method = "getAlarmTableListSQL")
    List<AlarmViewModel> getAlarmTableList(@Param("departments") List<Long> departments,@Param("departmentId") long departmentId,@Param("lineId") long lineId, @Param("type1") long type1, @Param("type2") long type2, @Param("date1") String date1, @Param("date2") String date2, @Param("keywords") String keywords);

    @SelectProvider(type = AlarmProvider.class,method = "getMapAlarmListSQL")
    List<AlarmViewModel> getMapAlarmTableList(@Param("devices") String devices,@Param("starttime") String starttime);

    @SelectProvider(type = AlarmProvider.class,method = "getAlarmViewModelSQL")
    AlarmViewModel getAlarmViewModel(@Param("id") long id);

    @Select("select count(a.id) from TramAlarmInfo a left join trambasicinfo b on a.AlarmType=b.Id where  a.DeviceId = #{deviceId} and b.Level=#{level}")
    long getCountByDeviceAndLevel(@Param("deviceId") long deviceId,@Param("level") int level);

    @SelectProvider(type = AlarmProvider.class,method = "getTop6AlarmSQL")
    List<HomeAlarmViewModel> getTop6Alarms(@Param("devices") List<Long> devices);
}
