package com.sztvis.buscloud.model.entity;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/11 下午5:04
 */
public enum HostDataType {
    HEALTH(0),GPS(1),CAN(2),DISPATCH(3),ALARM(4),HVNVR(5),RADAR(6),HARDWARESTATE(8),REALTIMEDVRSTATE(9);

    private HostDataType(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    private int value;

}
