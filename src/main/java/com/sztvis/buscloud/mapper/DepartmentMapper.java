package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.Tramdepartmentinfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/28 下午5:21
 */
@Repository
public interface DepartmentMapper {
    /**
     * Get DepartmentInfo by Id
     * @param Id
     * @return
     */
    @Select("select Id,Guid,Code,DepartmentName,DepartmentType,ParentId,ContactPhone,Sort,IsLookCan,IsHaveVedio,OrgType,AppName,Remark,CreateTime from TramDepartmentInfo where Id=#{Id}")
    Tramdepartmentinfo GetDepartmentInfo(Long Id);

    /**
     *
     * @param departmentIds
     * @return
     */
    @Select("select * from TramDepartmentInfo where Id in #{departmentIds}")
    List<Tramdepartmentinfo> GetDepartmentList(List<Long> departmentIds);

    /**
     *
     * @param userId
     * @return
     */
    @Select("select * from TramDepartmentInfo a left join TramMemberInfo b on a.Id=b.OwnershipId where b.id=#{userId}")
    Tramdepartmentinfo GetDepartmentIdsByUserId(long userId);

    /**
     * 根据父Id获得机构列表
     * @param parentId
     * @return
     */
    @Select("select * from TramDepartmentInfo where parentId=#{parentId}")
    List<Tramdepartmentinfo> GetDepartmentsByParentId(long parentId);

}
