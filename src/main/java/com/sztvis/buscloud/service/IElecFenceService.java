package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.amap.GeoStatusResult;
import com.sztvis.buscloud.model.amap.GeofenceFrom;
import com.sztvis.buscloud.model.amap.GeofenceResult;
import com.sztvis.buscloud.model.domain.TramElectronicFenceInfo;

import java.util.List;

public interface IElecFenceService {

    List<TramElectronicFenceInfo> getElecFenceList();

    TramElectronicFenceInfo getElecFenceInfo(long id);

    void insertElecFanceInfo(TramElectronicFenceInfo info);

    GeofenceResult saveGeoFence(GeofenceFrom geofenceFrom);

    GeofenceResult updateGeoFence(GeofenceFrom geofenceFrom,String gid);

    GeoStatusResult StartMonitor(String key,String diu,String locations);
}
