package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.mapper.provider.AlarmProvider;
import com.sztvis.buscloud.mapper.provider.CanProvider;
import com.sztvis.buscloud.model.HomealramViewModel;
import com.sztvis.buscloud.model.domain.TramAlarmInfo;
import com.sztvis.buscloud.model.domain.TramMemberInfo;
import com.sztvis.buscloud.model.dto.AlarmViewModel;
import com.sztvis.buscloud.model.dto.CanAlarmInfo;
import com.sztvis.buscloud.model.dto.HomeAlarmViewModel;
import com.sztvis.buscloud.model.dto.api.AppAlarmChartModel;
import com.sztvis.buscloud.model.entity.PageBean;
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
    List<AlarmViewModel> getAlarmTableList(@Param("departments") List<Long> departments,@Param("departmentId") long departmentId,@Param("lineId") long lineId, @Param("type1") long type1, @Param("type2") long type2, @Param("date1") String date1, @Param("date2") String date2, @Param("keywords") String keywords,@Param("offset") int offset,@Param("limit") int limit);
    @SelectProvider(type = AlarmProvider.class,method = "getAlarmTableListSQLCount")
    int getAlarmTableListCount(@Param("departments") List<Long> departments,@Param("departmentId") long departmentId,@Param("lineId") long lineId, @Param("type1") long type1, @Param("type2") long type2, @Param("date1") String date1, @Param("date2") String date2, @Param("keywords") String keywords);

    @SelectProvider(type = AlarmProvider.class,method = "getMapAlarmListSQL")
    List<AlarmViewModel> getMapAlarmTableList(@Param("devices") String devices,@Param("starttime") String starttime);

    @SelectProvider(type = AlarmProvider.class,method = "getAlarmViewModelSQL")
    AlarmViewModel getAlarmViewModel(@Param("id") long id);

    @Select("select count(a.id) from TramAlarmInfo a left join trambasicinfo b on a.AlarmType=b.Id where  a.DeviceId = #{deviceId} and b.Level=#{level}")
    long getCountByDeviceAndLevel(@Param("deviceId") long deviceId,@Param("level") int level);

    @SelectProvider(type = AlarmProvider.class,method = "getTop6AlarmSQL")
    List<HomeAlarmViewModel> getTop6Alarms(@Param("devices") List<Long> devices);

    @SelectProvider(type = AlarmProvider.class,method = "getAlarmCountByUserId")
    int getAlarmTrendsCounts(@Param("devices") List<Long> devices,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("type") int type);

    @SelectProvider(type = AlarmProvider.class,method = "GetListSQL")
    List<AlarmViewModel> GetList(@Param("DeviceIds") List<Long> DeviceIds,@Param("AlarmKeys") List<Long> AlarmKeys,@Param("userId") Long userId,@Param("dayType") int dayType,@Param("typeId") Long typeId,@Param("alarmType") Long alarmType,@Param("code") String code,@Param("lineId") Long lineId);

    @Select("select * from TramCanAlarmInfo where Id=#{Id}")
    CanAlarmInfo GetCanAlarmInfo(int Id);

    @Select("select count(Id) from TramCanAlarmInfo where deviceId=#{deviceId} and AlarmKey=#{AlarmKey} and UpdateTime>='#{UpdateTime}' and UpdateTime<='#{UpdateTime1}'")
    int CountId(@Param("deviceId") Long deviceId,@Param("AlarmKey") Long AxisId,@Param("UpdateTime") String UpdateTime,@Param("UpdateTime1") String UpdateTime1);

    @SelectProvider(type = AlarmProvider.class,method = "GetAppAlarmChartsSQL")
    int GetAppAlarmChartsSQL1(@Param("Id") Long Id,@Param("type") int type,@Param("startTime") String startTime,@Param("lineId") Long lineId,@Param("endTime") String endTime,@Param("deviceIds") List<Long> deviceIds);

    @SelectProvider(type = AlarmProvider.class,method = "xValCharsSQL")
    List<AppAlarmChartModel.TypeModel4> TypeModel4SQL(@Param("roleLv") Long roleLv,@Param("type") int type,@Param("Id") Long Id);

    @SelectProvider(type = AlarmProvider.class,method = "xValCharsSQL")
    List<AppAlarmChartModel.xVal> xValCharsSQL(@Param("roleLv") Long roleLv,@Param("type") int type,@Param("Id") Long Id);

    @Select("select Id,DeviceCode as Code,AlarmKey,updateTime from TramCanAlarmInfo where UpdateTime>=#{Date1} and UpdateTime<=#{Date2} and IsShow=1 and state=0 and DeviceId in (#{DeviceIds}) and AlarmKey in (#{AlarmKeys}) order by updateTime desc limit 10")
    List<HomealramViewModel> GetTop10Alarms(@Param("Date1") String date1,@Param("Date2") String date2,@Param("DeviceIds") String DeviceIds,@Param("AlarmKeys") String AlarmKeys);
}

