package com.sztvis.buscloud.model.dto;

import com.sztvis.buscloud.model.domain.TramDeviceStateInspectRealtimeInfo;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/31 下午3:45
 */
public class DeviceInspectViewModel extends TramDeviceStateInspectRealtimeInfo {
    private long departmentid;
    private String departmentname;
    private long lineid;
    private String linename;

    public long getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(long departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public long getLineid() {
        return lineid;
    }

    public void setLineid(long lineid) {
        this.lineid = lineid;
    }

    public String getLinename() {
        return linename;
    }

    public void setLinename(String linename) {
        this.linename = linename;
    }
}
