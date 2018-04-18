package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.dto.MaintenanceInfo;

import java.util.List;

public interface IMaintenanceService {
    List<MaintenanceInfo> GetCurrentAccountsMaintenanceList(long userId);

    void AddMaintenanceInfo(MaintenanceInfo maintenanceInfo);

    List<MaintenanceInfo> GetMaintenanceInfo(String Code,long DepartmentId,long lineId,String start,String end);

    List<MaintenanceInfo> getTop8Maintenaces();
}
