package com.sztvis.buscloud.model.dto.api;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/12 上午10:48
 */
public class GpsModel {
    private String Code;
    private String UpdateTime;
    private Double Lon;
    private Double Lat;
    private Double Speed;
    private Double Direct;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public Double getLon() {
        return Lon;
    }

    public void setLon(Double lon) {
        Lon = lon;
    }

    public Double getLat() {
        return Lat;
    }

    public void setLat(Double lat) {
        Lat = lat;
    }

    public Double getSpeed() {
        return Speed;
    }

    public void setSpeed(Double speed) {
        Speed = speed;
    }

    public Double getDirect() {
        return Direct;
    }

    public void setDirect(Double direct) {
        Direct = direct;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    private String Model;

}
