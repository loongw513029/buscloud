package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.Tramlineinfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午6:23
 */
@Repository
public interface LineMapper {

    @Select("select * from TramLineInfo where deparentId=#{departmentId} order by sort asc")
    List<Tramlineinfo> GetLinesByDepartmentId(long departmentId);

    @Select("select count(Id) from TramLineInfo where deparentId in #{departmentId}")
    Integer GetLineIdsByDepartmentIds(List<Long> departmentId);

}
