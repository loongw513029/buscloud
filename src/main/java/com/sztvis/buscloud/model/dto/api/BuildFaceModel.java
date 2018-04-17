package com.sztvis.buscloud.model.dto.api;

public class BuildFaceModel {

    private String code;//设备编码
    private String image;//base64编码图片
    private String updateTime;//数据时间

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
