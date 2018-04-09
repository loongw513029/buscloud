package com.sztvis.buscloud.model.entity;

public enum UnSafeBehaviorTypes {
    CarUnStopingThenOpenDoor(1,"车辆未停稳开车门"),
    CarGoingThenUnCloseDoor(2,"车辆起步不关车门"),
    NeutralAndTravel(3,"空档滑行"),
    ReversingSpeeding(4,"倒车超速"),
    TravelAtNight(5,"夜间行驶"),
    StartTravelSpeeding(6,"起步急加速"),
    EngineStalledTravel(7,"熄火滑行"),
    RevvingUp(8,"急加速"),
    QuickSlowDown(9,"急减速"),
    EmergencyBrake(10,"急刹车"),
    UncivilizedWhistle(11,"不文明鸣笛"),
    ZebraCrossingUnComity(12,"斑马线未礼让"),
    SpeedingTravel(13,"超速");

    private int value;
    private String explian;

    UnSafeBehaviorTypes(int value, String explian){
        this.value=value;
        this.explian=explian;
    }

    public static UnSafeBehaviorTypes get(int V){
        String str=String.valueOf(V);
        return get(str);
    }

    public static UnSafeBehaviorTypes get(String str){
        for (UnSafeBehaviorTypes u : values()){
            if (u.toString().equals(str))
                return u;
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getExplian() {
        return explian;
    }
}
