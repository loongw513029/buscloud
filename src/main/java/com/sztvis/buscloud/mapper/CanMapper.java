package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.mapper.provider.CanProvider;
import com.sztvis.buscloud.model.domain.CanHistoryEveryDayInfo;
import com.sztvis.buscloud.model.domain.TramCanInfo;
import com.sztvis.buscloud.model.domain.TramUnsafeBehaviorInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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

    @Select("select * from TramCanInfo where DeviceId=#{code} and updateTime<=#{time} order by updatetime desc limit 1")
    TramCanInfo GetCanInfoByLastTime(@Param("code")Long code,@Param("time")String time);

    @Select("select count(Id) from TramCanInfo where DeviceCode=#{devicecode} and UpdateTime>=#{startTime} and UpdateTime<=#{endTime}")
    int GetCanInfoBy10sTime(@Param("devicecode") String DeviceCode,@Param("startTime") String StartTime,@Param("endTime") String EndTime);

    @Select("select count(Id) from TramCanAlarmInfo where UpdateTime>=#{date1} and UpdateTime<=#{date2} and IsShow=1 and state=0 and DeviceId in(#{DeviceIds}) and AlarmKey in (#{AlarmKeys})")
    int GetTotal(@Param("date1") String date1,@Param("date2") String date2,@Param("DeviceIds") String DeviceIds,@Param("AlarmKeys") String AlarmKeys);

    @SelectProvider(type = CanProvider.class,method = "CalcDeviceCanHistorysSQL")
    void GetTop1CanInfo(@Param("type")String type,@Param("info")CanHistoryEveryDayInfo info);
    String GetTop1CanInfo(@Param("type")String type,@Param("deviceId")long deviceId,@Param("start")String start);
    int GetTop1CanInfo(@Param("type")String type,@Param("deviceId")long deviceId,@Param("start")String start,@Param("end")String end);
    Double GetTop1CanInfo(@Param("type")String type,@Param("devicecode")String devicecode,@Param("start")String start,@Param("end")String end);
    int GetTop1CanInfo(@Param("type")String type,@Param("deviceId")long deviceId,@Param("start")String start,@Param("end")String end,@Param("arr")int[] arr);

}
