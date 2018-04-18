package com.sztvis.buscloud.model.dto.api;

import java.util.List;

public class SchoolFaceFrom {
    private String code;
    private String updateTime;
    //指纹通过?
    private boolean fingerPrint;
    //酒驾？
    private boolean drunkDrive;
    //人脸通过？
    private boolean faceCompair;
    //相似度
    private double similar;
    private List<String> images;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(boolean fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public boolean isDrunkDrive() {
        return drunkDrive;
    }

    public void setDrunkDrive(boolean drunkDrive) {
        this.drunkDrive = drunkDrive;
    }

    public boolean isFaceCompair() {
        return faceCompair;
    }

    public void setFaceCompair(boolean faceCompair) {
        this.faceCompair = faceCompair;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public double getSimilar() {
        return similar;
    }

    public void setSimilar(double similar) {
        this.similar = similar;
    }
}
