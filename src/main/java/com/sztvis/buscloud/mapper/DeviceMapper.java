package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.Tramdeviceinfo;
import org.apache.ibatis.annotations.Select;
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
    List<Long> GetLineIdsByDepartmentId(Long parentId);

    @Select("select DeviceCode from TramDeviceInfo where lineId in #{lineIds}")
    List<String> GetDeviceCodesByLineIds(List<Long> lineIds);

    @Select("select deviceCode from TramDeviceInfo")
    List<String> GetDeviceCodes();

    @Select("select DeviceCode from TramDeviceInfo where DepartmentId in(select Id from TramDepartmentInfo where Id=#{departmentId} or ParentId=#{departmentId})")
    List<String> GetDeviceCodesByDepartmentId(Long departmentId);

    @Select("select * from TramDeviceInfo where lineId=#{lineId}")
    List<Tramdeviceinfo> GetDevicesByLineId(long lineId);

    @Select("select count(Id) from TramDeviceInfo where deviceStatus=#{state} and departmentId in #{departments}")
    Integer GetDeviceCount(int state,List<Long> departments);

}
