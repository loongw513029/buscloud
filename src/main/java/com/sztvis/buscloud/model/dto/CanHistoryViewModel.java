package com.sztvis.buscloud.model.dto;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/3/6 上午11:17
 */
public class CanHistoryViewModel {
    private long lineId;
    private int totalNumber;
    private int operaterNumber;
    private double totalMileage;
    private double fuelEconomy;
    private double elecEconomy;
    private double gasEconomy;
    private int faultNumber;
    private int faultBus;
    private List<String> categories;
    private List<List<Integer>> faults;
    private List<List<Integer>> unsafes;

    public long getLineId() {
        return lineId;
    }

    public void setLineId(long lineId) {
        this.lineId = lineId;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getOperaterNumber() {
        return operaterNumber;
    }

    public void setOperaterNumber(int operaterNumber) {
        this.operaterNumber = operaterNumber;
    }

    public double getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(double totalMileage) {
        this.totalMileage = totalMileage;
    }

    public double getFuelEconomy() {
        return fuelEconomy;
    }

    public void setFuelEconomy(double fuelEconomy) {
        this.fuelEconomy = fuelEconomy;
    }

    public double getElecEconomy() {
        return elecEconomy;
    }

    public void setElecEconomy(double elecEconomy) {
        this.elecEconomy = elecEconomy;
    }

    public double getGasEconomy() {
        return gasEconomy;
    }

    public void setGasEconomy(double gasEconomy) {
        this.gasEconomy = gasEconomy;
    }

    public int getFaultNumber() {
        return faultNumber;
    }

    public void setFaultNumber(int faultNumber) {
        this.faultNumber = faultNumber;
    }

    public int getFaultBus() {
        return faultBus;
    }

    public void setFaultBus(int faultBus) {
        this.faultBus = faultBus;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<List<Integer>> getFaults() {
        return faults;
    }

    public void setFaults(List<List<Integer>> faults) {
        this.faults = faults;
    }

    public List<List<Integer>> getUnsafes() {
        return unsafes;
    }

    public void setUnsafes(List<List<Integer>> unsafes) {
        this.unsafes = unsafes;
    }
}
