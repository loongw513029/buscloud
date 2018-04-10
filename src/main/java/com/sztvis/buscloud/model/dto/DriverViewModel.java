package com.sztvis.buscloud.model.dto;

import com.sztvis.buscloud.model.domain.TramDriverInfo;

public class DriverViewModel extends TramDriverInfo {
    private String departmentname;

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
}
