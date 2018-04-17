package com.sztvis.buscloud.model.dto;

import com.sztvis.buscloud.model.domain.TramPersonInfo;

public class BuildFaceViewModel extends TramPersonInfo {
    private String images;
    private String departmentname;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
}
