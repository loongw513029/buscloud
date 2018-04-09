package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.Tramdriverinfo;
import com.sztvis.buscloud.model.dto.ComboTreeModel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/23 上午9:04
 */
@Repository
public interface DriverMapper {

    @Select("select id,drivername as text from tramDriverInfo where departmentid=#{departmentid}")
    List<ComboTreeModel> getDriverComboList(long departmentid);

    @Select("select id,DriverName,Gender,DepartmentId,Status from TramDriverInfo where status=1")
    List<Tramdriverinfo> GetTramdriverinfo();
}
