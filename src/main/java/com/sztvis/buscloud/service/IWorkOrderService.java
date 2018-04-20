package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.dto.WorkOrderViewModel;
import com.sztvis.buscloud.model.entity.PageBean;

import java.util.List;

public interface IWorkOrderService {
    List<WorkOrderViewModel> GetWorkOrders(String code,String start,String end);
}
