package com.sztvis.buscloud.model.dto.api;

public class DeviceFilterSearchResult {
    public long DeviceId;
    public String DeviceCode;
    public long LineId;

    public long getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(long deviceId) {
        DeviceId = deviceId;
    }

    public String getDeviceCode() {
        return DeviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        DeviceCode = deviceCode;
    }

    public long getLineId() {
        return LineId;
    }

    public void setLineId(long lineId) {
        LineId = lineId;
    }

    public String getLineName() {
        return LineName;
    }

    public void setLineName(String lineName) {
        LineName = lineName;
    }

    public String LineName;
}
