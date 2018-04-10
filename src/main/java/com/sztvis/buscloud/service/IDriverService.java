package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramDriverInfo;
import com.sztvis.buscloud.model.dto.DriverViewModel;

import java.util.List;

public interface IDriverService {
    List<DriverViewModel> getDriverList(String driverName, long departmentId, int offset, int limit);
    int getDriverListCount(String driverName, long departmentId);

    TramDriverInfo getDriverInfo(long id);

    void SaveAndUpdateDriver(TramDriverInfo driverInfo);

    void RemoveDrivers(List<String> ids);
}
