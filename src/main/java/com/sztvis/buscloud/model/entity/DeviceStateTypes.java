package com.sztvis.buscloud.model.entity;

public enum DeviceStateTypes {

    Videotape(1,"录像状态"),
    Video(2,"视频状态"),
    HardDisk(3,"硬盘状态"),
    SDCard(4,"SD卡状态"),
    CPUUseRate(5,"CPU使用率"),
    CPUTemp(6,"CPU温度"),
    MermoryUseRate(7,"内存使用率"),
    DiskTemp(8,"硬盘温度"),
    GpsState(9,"Gps即时状态"),
    CanState(10,"Can即时状态"),
    ADASState(11,"ADAS状态"),
    InternetState(12,"主机网络状态"),
    GpsSignelState(13,"Gps信号状态"),
    SIM(14,"SIM卡余额"),
    GpsInspectState(15,"Gps巡检状态"),
    CanInspectState(16,"CAN巡检状态"),
    RadarInspectState(17,"雷达巡检状态"),
    AdasInspectState(18,"Adas巡检状态"),
    BehaviorInspectState(19,"行为识别巡检状态");


    public int getValue() {
        return value;
    }

    public String getExplian() {
        return explian;
    }

    public void setExplian(String explian) {
        this.explian = explian;
    }

    private int value;
    private String explian;

    DeviceStateTypes(int value, String explian){
        this.value=value;
        this.explian=explian;
    }

    public static DeviceStateTypes get(int V){
        String str=String.valueOf(V);
        return get(str);
    }

    public static DeviceStateTypes get(String str){
        for (DeviceStateTypes u : values()){
            if (u.toString().equals(str))
                return u;
        }
        return null;
    }
}
