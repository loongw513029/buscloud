package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.mapper.provider.WorkOrderProvider;
import com.sztvis.buscloud.model.dto.WorkOrderViewModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface WorkOrderMapper {
    @SelectProvider(type = WorkOrderProvider.class,method = "GetWorkOrdersSQL")
    List<WorkOrderViewModel> GetWorkOrders(@Param("code") String code,@Param("start") String start,@Param("end") String end);
}
