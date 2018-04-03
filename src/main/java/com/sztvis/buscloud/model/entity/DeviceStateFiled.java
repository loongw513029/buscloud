package com.sztvis.buscloud.model.entity;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/11 下午6:01
 */
public enum  DeviceStateFiled {
    Videotape("Videotape"),Video("Video"),HardDisk("HardDisk"),SDCard("SDCard"),
    CPUUseRate("CPUUseRate"),CPUTemp("CPUTemp"),MemoryUseRate("MemoryUseRate"),DiskTemp("DiskTemp"),GpsState("GpsState"),
    CanState("CanState"),InternetState("InternetState"),GpsSignelState("GpsSignelState"),SIMBalance("SIMBalance"),
    GpsInspectState("GpsInspectState"),CanInspectState("CanInspectState"),BehaviorInspectState("BehaviorInspectState"),
    RadarInspectState("RadarInspectState"),AdasInspectState("AdasInspectState"),TimingState("TimingState"),OnlineState("OnlineState"),
    SurplusDiskSize("SurplusDiskSize"),SurplusSdcardSize("SurplusSdcardSize"),SIMCardNo("SimCardNo");
    private DeviceStateFiled(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private String value;

}
