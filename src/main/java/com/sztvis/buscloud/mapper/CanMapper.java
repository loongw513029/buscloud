package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.CanHistoryEveryDayInfo;
import com.sztvis.buscloud.model.domain.TramUnsafeBehaviorInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/3/1 下午3:19
 */
@Repository
public interface CanMapper {

    @Insert("insert into TramUnsafeBehaviorInfo(deviceid,devicecode,unsafetype,unsafetime,speed,ratespeed,applytime,location,createtime)values(#{deviceid},#{devicecode},#{unsafetype},#{unsafetime},#{speed},#{ratespeed},#{applytime},#{location},#{createtime})")
    void insertUnsafeInfo(TramUnsafeBehaviorInfo unsafeBehaviorInfo);

    @Select("select a.* from canhistoryeverydayinfo a left join tramdeviceinfo b on a.DeviceId=b.Id where b.LineId=#{lineId} and a.updateTime>=#{startTime} and a.updateTime<#{endTime}")
    List<CanHistoryEveryDayInfo> getCanHistoryDayInfo(@Param("lineId") long lineId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("select count(a.Id) from TramAlarmInfo a left join TramDeviceInfo b on a.deviceId=b.Id where b.lineId=#{lineId} and a.updateTime>=#{startTime} and a.updateTime<=#{endTime} and a.alarmType=#{type}")
    int getAlarmTrendsCountByLineId(@Param("lineId") long lineId,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("type") int type);

    @Select("select count(Id) from TramAlarmInfo where deviceId=#{deviceId} and updateTime>=#{startTime} and updateTime<=#{endTime} and alarmType=#{type}")
    int getAlarmTrendsCountByDeviceId(@Param("deviceId") long deviceId,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("type") int type);
}
