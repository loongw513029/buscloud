package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.domain.Tramdevicestateinspectrealtimeinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("select * from TramDeviceInfo where deviceCode=#{deviceCode}")
    TramDeviceInfo getDeviceInfoByCode(String deviceCode);

    @Update("update TramDeviceInfo set deviceStatus=#{value} where deviceCode=#{deviceCode}")
    void udpateDeviceState(String deviceCode,int value);

    @Select("select count(Id) from tramdevicestateinspectrealtimeinfo where deviceCode=#{deviceCode}")
    int getRealtimeInspectCount(String deviceCode);

    @Update("update tramdevicestateinspectrealtimeinfo set #{field}=#{value} where deviceCode=#{deviceCode}")
    void updateRealtimeInspect(String deviceCode,String field,Object value);

    @Insert("insert into tramdevicestateinspectrealtimeinfo(deviceid,videotape,video,harddisk,sdcard,cpuuserate,cputemp,mermoryuserate,disktemp,gpsstate,canstate,internetstate,gpssignelstate,simbalance,gpsinspectstate,caninspectstate,behaviorinspectstate,radarinspectstate,adasinspectstate,timingstate,deviceCode)values(#{deviceid},#{videotape},#{video},#{harddisk},#{sdcard},#{cpuuserate},#{cputemp},#{mermoryuserate},#{disktemp},#{gpsstate},#{canstate},#{internetstate},#{gpssignelstate},#{simbalance},#{gpsinspectstate},#{caninspectstate},#{behaviorinspectstate},#{radarinspectstate},#{adasinspectstate},#{timingstate},#{deviceCode})")
    void insertRealtimeInspect(Tramdevicestateinspectrealtimeinfo info);
}
