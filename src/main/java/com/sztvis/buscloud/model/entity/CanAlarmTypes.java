package com.sztvis.buscloud.model.entity;

public enum  CanAlarmTypes {
    ElectricTemperature(1,"电机温度报警"),
    BatteryTemperature(2,"电池温度报警"),
    HybridPowerSystem(3,"混合动力系统报警"),
    车辆倾斜报警(11,"车辆倾斜报警"),
    紧急按钮报警(12,"紧急按钮报警"),
    生命感知报警(13,"生命感知报警"),
    Gsensor(14,"Gsensor"),
    气压1报警(43,"气压1报警"),
    气压2报警(44,"气压2报警"),
    机油压力报警(45,"机油压力报警"),
    冷却水位报警(46,"冷却水位报警"),
    空气滤清报警(47,"空气滤清报警"),
    燃油滤清报警(48,"燃油滤清报警"),
    机油滤清报警(49,"机油滤清报警"),
    发动机仓温过高(50,"发动机仓温过高"),
    左前刹车片磨损(55,"左前刹车片磨损"),
    左中刹车片磨损(56,"左中刹车片磨损"),
    左后刹车片磨损(57,"左后刹车片磨损"),
    右前刹车片磨损(58,"右前刹车片磨损"),
    右中刹车片磨损(59,"右中刹车片磨损"),
    右后刹车片磨损(60,"右后刹车片磨损"),
    转速报警(61,"转速报警"),
    车速报警(62,"车速报警"),
    水温报警(63,"水温报警"),
    电压报警(64,"电压报警"),
    疲劳驾驶(68,"疲劳驾驶"),
    制动灯传感器报警(69,"制动灯传感器报警"),
    气压1传感器报警(70,"气压1传感器报警"),
    气压2传感器报警(71,"气压2传感器报警"),
    机油压力传感器报警(72,"机油压力传感器报警"),
    冷却水位传感器报警(73,"冷却水位传感器报警"),
    空气滤清传感器报警(74,"空气滤清传感器报警"),
    燃油滤清传感器报警(75,"燃油滤清传感器报警"),
    发动机仓温度传感器报警(76,"发动机仓温度传感器报警"),
    发动机启动传感器报警(77,"发动机启动传感器报警"),
    发动机诊断传感器报警(78,"发动机诊断传感器报警"),
    发动机保养传感器报警(79,"发动机保养传感器报警"),
    左前刹车片传感器报警(80,"左前刹车片传感器报警"),
    左中刹车片传感器报警(81,"左中刹车片传感器报警"),
    左后刹车片传感器报警(82,"左后刹车片传感器报警"),
    右前刹车片传感器报警(83,"右前刹车片传感器报警"),
    右中刹车片传感器报警(84,"右中刹车片传感器报警"),
    右后刹车片传感器报警(85,"右后刹车片传感器报警"),
    转速传感器报警(86,"转速传感器报警"),
    车速传感器报警(87,"车速传感器报警"),
    水温传感器报警(88,"水温传感器报警"),
    电压传感器报警(89,"电压传感器报警"),
    监视器传感器报警(90,"监视器传感器报警"),
    投币箱传感器报警(91,"投币箱传感器报警"),
    油水分离传感器报警(92,"油水分离传感器报警"),
    机油滤清传感器报警(93,"机油滤清传感器报警"),
    电子围栏报警(94,"电子围栏报警"),
    Videotape(95,"录像状态报警"),
    Vedio(96,"视频状态报警"),
    HDD(97,"硬盘状态报警"),
    SDCard(98,"SD卡状态报警"),
    LevelOneFatigue(99,"一级疲劳瞌睡"),
    LevelTwoFatigue(100,"二级疲劳瞌睡"),
    Smoking(101,"抽烟"),
    Calling(102,"打电话"),
    StaredDown(103,"低头"),
    Yawn(104,"打哈欠"),
    GazedAround(105,"左顾右盼"),
    Chating(106,"聊天"),
    LeavePost(107,"离岗"),
    Occlusion(108,"遮挡"),
    Radar(109,"防撞系统距离报警"),
    CPUUserRate(110,"CPU使用率过高"),
    CPUTemp(111,"CPU温度报警"),
    MemoryUseRate(112,"内存使用率过高"),
    DiskTemp(113,"硬盘温度报警"),
    ADAS(114,"ADAS"),
    SerialPortLose(117,"串口丢失报警"),
    CarDistanceRemind(1118,"车距提醒"),
    DangerDistance(119,"危险车距预警"),
    RollLeftRoad(120,"压左道"),
    RoolRightRoad(121,"压右道"),
    LowSpeedBump(122,"低速碰撞预警"),
    FaceBumpAlarm(123,"前向碰撞预警"),
    BumpPerson(124,"行人碰撞预警"),
    RapidAcceleration(125,"行人在危险区域"),
    RapidDeceleration(126,"急减速报警"),
    SharpTurn(127,"急转弯报警"),
    DischargeElectric(183,"电池组温度状态异常"),
    Leakage(184,"漏电报警"),
    WaterAlarm1(185,"水温警报1"),
    WaterAlarm2(186,"水温警报2");
    private int value;
    private String explian;

    CanAlarmTypes(int value, String explian){
        this.value=value;
        this.explian=explian;
    }

    public static CanAlarmTypes get(int V){
        String str=String.valueOf(V);
        return get(str);
    }

    public int getValue() {
        return value;
    }

    public String getExplian() {
        return explian;
    }

    public void setExplian(String explian) {
        this.explian = explian;
    }

    public static CanAlarmTypes get(String str){
        for (CanAlarmTypes u : values()){
            if (u.toString().equals(str))
                return u;
        }
        return null;
    }
}
