package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.dto.MaintenanceInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceMapper {

    @Insert("insert into MaintenanceInfo(DeviceId,MtDate,MtMileage,Project,NextDate,NextMileage,Description,CreateTime)values(#{DeviceId},#{MtDate},#{MtMileage},#{Project},#{NextDate},#{NextMileage},#{Description},#{CreateTime})")
    void AddMaintenanceInfo(MaintenanceInfo maintenanceInfo);

    @Select("select a.Id,a.DeviceId,a.MtDate,a.MtMileage,a.Project,a.NextDate,a.NextMileage,a.Description,a.CreateTime,b.DeviceCode from MaintenanceInfo a left join TramDeviceInfo b on a.DeviceId=b.Id where a.deviceId in (#{deviceIds}) order by a.createtime desc")
    List<MaintenanceInfo> GetCurrentAccountsMaintenanceList(String deviceIds);
}
