package com.sztvis.buscloud.model.domain;

import java.io.Serializable;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/10 上午9:29
 */
public class ElectricCanInfo implements Serializable{
    private long canId;
    private int batterySingleCount;
    private int batteryProbeCount;
    private String batteryMaxVoltageSingleCode;
    private String batteryMaxVoltage;
    private String batteryMinVoltageCode;
    private String batteryMinVoltage;
    private String maxTempProbeNumber;
    private String maxTempValue;
    private String minTempProbeNumber;
    private String minTempValue;
    private String highResistance;
    private String electrical;
    private String leftElecRote;
    private String leftElecInputVoltage;
    private String leftElecTemp;
    private String leftElecContrTemp;
    private String leftElecTorque;
    private String leftElecMode;
    private String leftElecCurrent;
    private String rightElecRote;
    private String rightElecInputVoltage;
    private String rightELecTemp;
    private String rightElecContrTemp;
    private String rightElecTorque;
    private String rightElecMode;
    private String rightElecCurrent;
    private String acceleratorPedal;
    private String carVIN;

    public long getCanId() {
        return canId;
    }

    public void setCanId(long canId) {
        this.canId = canId;
    }

    public int getBatterySingleCount() {
        return batterySingleCount;
    }

    public void setBatterySingleCount(int batterySingleCount) {
        this.batterySingleCount = batterySingleCount;
    }

    public int getBatteryProbeCount() {
        return batteryProbeCount;
    }

    public void setBatteryProbeCount(int batteryProbeCount) {
        this.batteryProbeCount = batteryProbeCount;
    }

    public String getBatteryMaxVoltageSingleCode() {
        return batteryMaxVoltageSingleCode;
    }

    public void setBatteryMaxVoltageSingleCode(String batteryMaxVoltageSingleCode) {
        this.batteryMaxVoltageSingleCode = batteryMaxVoltageSingleCode;
    }

    public String getBatteryMaxVoltage() {
        return batteryMaxVoltage;
    }

    public void setBatteryMaxVoltage(String batteryMaxVoltage) {
        this.batteryMaxVoltage = batteryMaxVoltage;
    }

    public String getBatteryMinVoltageCode() {
        return batteryMinVoltageCode;
    }

    public void setBatteryMinVoltageCode(String batteryMinVoltageCode) {
        this.batteryMinVoltageCode = batteryMinVoltageCode;
    }

    public String getBatteryMinVoltage() {
        return batteryMinVoltage;
    }

    public void setBatteryMinVoltage(String batteryMinVoltage) {
        this.batteryMinVoltage = batteryMinVoltage;
    }

    public String getMaxTempProbeNumber() {
        return maxTempProbeNumber;
    }

    public void setMaxTempProbeNumber(String maxTempProbeNumber) {
        this.maxTempProbeNumber = maxTempProbeNumber;
    }

    public String getMaxTempValue() {
        return maxTempValue;
    }

    public void setMaxTempValue(String maxTempValue) {
        this.maxTempValue = maxTempValue;
    }

    public String getMinTempProbeNumber() {
        return minTempProbeNumber;
    }

    public void setMinTempProbeNumber(String minTempProbeNumber) {
        this.minTempProbeNumber = minTempProbeNumber;
    }

    public String getMinTempValue() {
        return minTempValue;
    }

    public void setMinTempValue(String minTempValue) {
        this.minTempValue = minTempValue;
    }

    public String getHighResistance() {
        return highResistance;
    }

    public void setHighResistance(String highResistance) {
        this.highResistance = highResistance;
    }

    public String getElectrical() {
        return electrical;
    }

    public void setElectrical(String electrical) {
        this.electrical = electrical;
    }

    public String getLeftElecRote() {
        return leftElecRote;
    }

    public void setLeftElecRote(String leftElecRote) {
        this.leftElecRote = leftElecRote;
    }

    public String getLeftElecInputVoltage() {
        return leftElecInputVoltage;
    }

    public void setLeftElecInputVoltage(String leftElecInputVoltage) {
        this.leftElecInputVoltage = leftElecInputVoltage;
    }

    public String getLeftElecTemp() {
        return leftElecTemp;
    }

    public void setLeftElecTemp(String leftElecTemp) {
        this.leftElecTemp = leftElecTemp;
    }

    public String getLeftElecContrTemp() {
        return leftElecContrTemp;
    }

    public void setLeftElecContrTemp(String leftElecContrTemp) {
        this.leftElecContrTemp = leftElecContrTemp;
    }

    public String getLeftElecTorque() {
        return leftElecTorque;
    }

    public void setLeftElecTorque(String leftElecTorque) {
        this.leftElecTorque = leftElecTorque;
    }

    public String getLeftElecMode() {
        return leftElecMode;
    }

    public void setLeftElecMode(String leftElecMode) {
        this.leftElecMode = leftElecMode;
    }

    public String getLeftElecCurrent() {
        return leftElecCurrent;
    }

    public void setLeftElecCurrent(String leftElecCurrent) {
        this.leftElecCurrent = leftElecCurrent;
    }

    public String getRightElecRote() {
        return rightElecRote;
    }

    public void setRightElecRote(String rightElecRote) {
        this.rightElecRote = rightElecRote;
    }

    public String getRightElecInputVoltage() {
        return rightElecInputVoltage;
    }

    public void setRightElecInputVoltage(String rightElecInputVoltage) {
        this.rightElecInputVoltage = rightElecInputVoltage;
    }

    public String getRightELecTemp() {
        return rightELecTemp;
    }

    public void setRightELecTemp(String rightELecTemp) {
        this.rightELecTemp = rightELecTemp;
    }

    public String getRightElecContrTemp() {
        return rightElecContrTemp;
    }

    public void setRightElecContrTemp(String rightElecContrTemp) {
        this.rightElecContrTemp = rightElecContrTemp;
    }

    public String getRightElecTorque() {
        return rightElecTorque;
    }

    public void setRightElecTorque(String rightElecTorque) {
        this.rightElecTorque = rightElecTorque;
    }

    public String getRightElecMode() {
        return rightElecMode;
    }

    public void setRightElecMode(String rightElecMode) {
        this.rightElecMode = rightElecMode;
    }

    public String getRightElecCurrent() {
        return rightElecCurrent;
    }

    public void setRightElecCurrent(String rightElecCurrent) {
        this.rightElecCurrent = rightElecCurrent;
    }

    public String getAcceleratorPedal() {
        return acceleratorPedal;
    }

    public void setAcceleratorPedal(String acceleratorPedal) {
        this.acceleratorPedal = acceleratorPedal;
    }

    public String getCarVIN() {
        return carVIN;
    }

    public void setCarVIN(String carVIN) {
        this.carVIN = carVIN;
    }
}
