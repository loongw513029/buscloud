package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.mapper.provider.DeviceSqlProvider;
import com.sztvis.buscloud.model.domain.*;
import com.sztvis.buscloud.model.dto.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/28 下午5:40
 */
@Repository
public interface DeviceMapper {


    @Select("select Id from TramLineInfo where DeparentId in (select Id from TramDepartmentInfo where Id=#{parentId})")
    List<Long> getLineIdsByDepartmentId(Long parentId);

    @Select("select DeviceCode from TramDeviceInfo where lineId in #{lineIds}")
    List<String> getDeviceCodesByLineIds(List<Long> lineIds);

    @Select("select deviceCode from TramDeviceInfo")
    List<String> getDeviceCodes();

    @Select("select DeviceCode from TramDeviceInfo where DepartmentId in(select Id from TramDepartmentInfo where Id=#{departmentId} or ParentId=#{departmentId})")
    List<String> getDeviceCodesByDepartmentId(Long departmentId);

    @Select("select * from TramDeviceInfo where lineId=#{lineId}")
    List<TramDeviceInfo> getDevicesByLineId(long lineId);

    @Select("select count(Id) from TramDeviceInfo where deviceStatus=#{state} and departmentId in #{departments}")
    Integer getDeviceCount(int state,List<Long> departments);

    @Select("select count(Id) from TramDeviceInfo where departmentId in #{departments} and LastOnlineTime>=#{startTime}")
    Integer getOnlinePrecent(List<Long> departments,String startTime);

    @Select("select count(Id) from tramunsafebehaviorinfo where deviceId in #{departments} and ApplyTime>#{startTime}")
    Integer getUnSafeCountByDepartmentIds(List<Long> departments,String startTime);

    @Select("select * from TramDeviceInfo where id =#{deviceId}")
    TramDeviceInfo getDeviceInfoById(long deviceId);

    @Select("select id from TramDeviceInfo where devicecode=#{deviceCode}")
    long getDeviceIdByCode(String deviceCode);

    @Select("select * from TramDeviceInfo where deviceCode=#{deviceCode}")
    TramDeviceInfo getDeviceInfoByCode(String deviceCode);

    @Update("update TramDeviceInfo set deviceStatus=#{value} where deviceCode=#{deviceCode}")
    void udpateDeviceState(String deviceCode,int value);

    @Select("select count(Id) from tramdevicestateinspectrealtimeinfo where deviceCode=#{deviceCode}")
    int getRealtimeInspectCount(String deviceCode);

    /**
     * 修改巡检状态
     * @param deviceCode 设备编码
     * @param field 字段名
     * @param value 字段值
     * @param fieldtype 字段类型 1:字符串，2：Double/Int, 3:boolean
     */
    @UpdateProvider(type = DeviceSqlProvider.class,method = "updateRealtimeInspectSQL")
    void updateRealtimeInspect(@Param("deviceCode") String deviceCode,@Param("field") String field,@Param("value") Object value,@Param("fieldtype") int fieldtype);

    @Insert("insert into tramdevicestateinspectrealtimeinfo(deviceid,videotape,video,harddisk,sdcard,cpuuserate,cputemp,mermoryuserate,disktemp,gpsstate,canstate,internetstate,gpssignelstate,simbalance,gpsinspectstate,caninspectstate,behaviorinspectstate,radarinspectstate,adasinspectstate,timingstate,deviceCode,surplusDiskSize,surplusSdcardSize)values(#{deviceid},#{videotape},#{video},#{harddisk},#{sdcard},#{cpuuserate},#{cputemp},#{mermoryuserate},#{disktemp},#{gpsstate},#{canstate},#{internetstate},#{gpssignelstate},#{simbalance},#{gpsinspectstate},#{caninspectstate},#{behaviorinspectstate},#{radarinspectstate},#{adasinspectstate},#{timingstate},#{deviceCode},#{surplusDiskSize},#{surplusSdcardSize})")
    void insertRealtimeInspect(TramDeviceStateInspectRealtimeInfo info);

    @SelectProvider(type = DeviceSqlProvider.class,method = "getWebListSQL")
    List<DeviceViewModel> getBusList(@Param("departments") List<Long> departments,
                                     @Param("devicetype") int devicetype,
                                     @Param("departmentId") long departmentId,
                                     @Param("lineId") long lineId,
                                     @Param("status") int status,
                                     @Param("keywords") String keywords
                                     );
    @SelectProvider(type = DeviceSqlProvider.class,method = "getDeviceViewModel")
    DeviceViewModel getDeviceViewModel(@Param("id") long id);

    @Select("select * from tramdevicestateinspectrealtimeinfo where deviceid=#{deviceid}")
    TramDeviceStateInspectRealtimeInfo getDeviceStateInspectRealTimeInfo(long deviceid);

    @Select("select a.id,a.devicecode,a.devicename,a.hostsofttype,a.busid,a.clientip,a.devicemode,a.disksize,a.sdcardsize,a.videosupport,a.videochannel,a.dchannel,a.carriagechannel,a.aerialview,a.aerialchannel,a.barrier,a.can,a.radar,a.supportbehavior,a.supportadas,a.speeduse,b.busnumber,b.busframenumber,b.bustype,a.departmentid,a.lineid,b.driverid from tramdeviceinfo a left join trambusinfo b on a.busid=b.id where a.id=#{id}")
    BusAndDeviceViewModel getBusAndDeviceInfo(long id);

    @Select("select channelname,no,supportptz from TramChannelInfo where deviceid =#{id}")
    List<ChannelViewModel> getChannelViewModelList(long id);

    @Insert("insert into TramDeviceInfo(guid,busid,departmentid,lineid,devicecode,devicename,clientip,ispositive,videosupport,videochannel,dchannel,carriagechannel,devicetypeid,canbustypeid,devicemode,hostsofttype,disksize,sdcardsize,devicestatus,aerialview,aerialchannel,barrier,can,radar,supportbehavior,supportadas,passengerflow,speeduse,createtime,lastonlinetime)values(#{guid},#{busid},#{departmentid},#{lineid},#{devicecode},#{devicename},#{clientip},#{ispositive},#{videosupport},#{videochannel},#{dchannel},#{carriagechannel},#{devicetypeid},#{canbustypeid},#{devicemode},#{hostsofttype},#{disksize},#{sdcardsize},#{devicestatus},#{aerialview},#{aerialchannel},#{barrier},#{can},#{radar},#{supportbehavior},#{supportadas},#{passengerflow},#{speeduse},#{createtime},#{lastonlinetime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertDeviceInfo(TramDeviceInfo deviceInfo);

    @Update("update TramDeviceInfo set busid=#{busid},departmentid=#{departmentid},lineid=#{lineid},devicecode=#{devicecode},devicename=#{devicename},clientip=#{clientip},videosupport=#{videosupport},videochannel=#{videochannel},dchannel=#{dchannel},carriagechannel=#{carriagechannel},devicemode=#{devicemode},hostsofttype=#{hostsofttype},disksize=#{disksize},sdcardsize=#{sdcardsize},aerialview=#{aerialview},aerialchannel=#{aerialchannel},barrier=#{barrier},can=#{can},radar=#{radar},supportbehavior=#{supportbehavior},supportadas=#{supportadas},speeduse=#{speeduse} where id=#{id}")
    void updateDeviceInfo(TramDeviceInfo deviceInfo);

    @Insert("insert into TramBusInfo(guid,busplate,departmentid,lineid,bustype,busmode,busnumber,busframenumber,driverid,busstatus,createtime)values(#{guid},#{busplate},#{departmentid},#{lineid},#{bustype},#{busmode},#{busnumber},#{busframenumber},#{driverid},#{busstatus},#{createtime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertBusInfo(TramBusInfo busInfo);

    @Update("update TramBusInfo set departmentid=#{departmentid},lineid=#{lineid},busnumber=#{busnumber},busframenumber=#{busframenumber},driverid=#{driverid} where id=#{id}")
    void updateBusInfo(TramBusInfo busInfo);

    @Insert("insert into TramChannelInfo(deviceid,no,channelname,supportptz)values(#{deviceid},#{no},#{channelname},#{supportptz})")
    void insertChannelInfo(TramChannelInfo channelInfo);

    @Delete("delete from TramDeviceInfo where id in(#{ids})")
    void removeDeviceInfo(String ids);

    @Delete("delete from TramBusInfo where id in (#{ids})")
    void removeBusInfo(String ids);

    @Delete("delete from TramChannelInfo where deviceid=#{deviceid}")
    void removeChannelInfo(long deviceid);

    @Delete("delete from TramChannelInfo where deviceid in(#{deviceIds})")
    void removeChannels(String deviceIds);

    @Select("select busid from TramDeviceInfo where id in(#{deviceIds})")
    List<Long> getBusIds(String deviceIds);

    //@Select("select a.id,a.devicecode,b.departmentname,c.linename,e.busnumber from tramdeviceinfo a left join trambusinfo e on a.busid=e.id left join tramdepartmentinfo b on a.departmentid=b.id left join tramlineinfo c on a.lineid=c.id where a.id in(#{devices})")
    @SelectProvider(type = DeviceSqlProvider.class,method = "getMapDeviceListSQL")
    List<MapDeviceViewModel> getMapDeviceList(@Param("devices") String devices);

    @SelectProvider(type = DeviceSqlProvider.class,method = "getDeviceInspectSQL")
    List<DeviceInspectViewModel> getDeviceInspectList(@Param("departments") List<Long> departmens,@Param("departmentid") long departmentid,@Param("lineid") long lineid,@Param("type") int type,@Param("keywords") String keywords);

    @Select("select * from TramDeviceInfo")
    List<TramDeviceInfo> getAllDevices();

    @Insert("insert into CanHistoryEveryDayInfo(deviceid,updatetime,totalmileage,gasonlieavg,electricavg,gasavg,totalfaultnumber,totalcarfaultnumber,faultthreelv,faultsecondlv,faultonelv,unsafenumber,unsafedriver,speedingtotal,runtimelong)values(#{deviceid},#{updatetime},#{totalmileage},#{gasonlieavg},#{electricavg},#{gasavg},#{totalfaultnumber},#{totalcarfaultnumber},#{faultthreelv},#{faultsecondlv},#{faultonelv},#{unsafenumber},#{unsafedriver},#{speedingtotal},#{runtimelong})")
    void insertCanHistoryEveryDayData(CanHistoryEveryDayInfo info);

    @Select("select count(id) from tramunsafebehaviorinfo where deviceId=#{deviceId} and ApplyTime>=#{time1} and ApplyTime<=#{time2}")
    long getUnsafeCountByDeviceIdEveryDay(@Param("deviceId") long deviceId,@Param("time1") String time1,@Param("time2") String time2);

    @Update("update TramDeviceInfo set deviceStatus=#{status} where devicecode=#{deviceCode}")
    void updateDeviceStatus(@Param("deviceCode") String deviceCode,@Param("status") int status);

    @Select("select * from TramBusInfo where id=#{busId}")
    TramBusInfo getBusInfo(long busId);

}
