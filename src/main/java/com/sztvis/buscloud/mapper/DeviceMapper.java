package com.sztvis.buscloud.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/28 下午5:40
 */
public interface DeviceMapper {


    @Select("select Id from TramLineInfo where DeparentId in (select Id from TramDepartmentInfo where Id=#{parentId})")
    List<Long> GetLineIdsByDepartmentId(Long parentId);

    @Select("select DeviceCode from TramDeviceInfo where lineId in #{lineIds}")
    List<String> GetDeviceCodesByLineIds(List<Long> lineIds);

    @Select("select deviceCode from TramDeviceInfo")
    List<String> GetDeviceCodes();

    @Select("select DeviceCode from TramDeviceInfo where DepartmentId in(select Id from TramDepartmentInfo where Id=#{departmentId} or ParentId=#{departmentId})")
    List<String> GetDeviceCodesByDepartmentId(Long departmentId);

}
