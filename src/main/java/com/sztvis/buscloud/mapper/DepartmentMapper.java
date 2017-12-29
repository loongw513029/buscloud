package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.Tramdepartmentinfo;
import org.apache.ibatis.annotations.Select;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/28 下午5:21
 */
public interface DepartmentMapper {
    /**
     * Get DepartmentInfo by Id
     * @param Id
     * @return
     */
    @Select("select Id,Guid,Code,DepartmentName,DepartmentType,ParentId,ContactPhone,Sort,IsLookCan,IsHaveVedio,OrgType,AppName,Remark,CreateTime from TramDepartmentInfo where Id=#{Id}")
    Tramdepartmentinfo GetDepartmentInfo(Long Id);
}
