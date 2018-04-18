package com.sztvis.buscloud.config;


import com.sztvis.buscloud.service.ICanService;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IFlowService;
import com.sztvis.buscloud.service.IUnSafeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * 定时任务类
 * @author longweiqian
 * @company tvis
 * @date 2018/1/3 上午10:22
 */
@Component
@Configuration
public class ScheduledTask {

    private static Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private IDeviceService iDeviceService;
    @Autowired
    private IUnSafeService iUnSafeService;
    @Autowired
    private IFlowService iFlowService;
    @Autowired
    private ICanService iCanService;

    /**
     * 定时巡检设备在线状态,每分钟查询巡检表然后送推到页面上
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void IntervalOnlineTask(){
        this.iDeviceService.autoDeviceStatus();
    }

    /**
     * 每天零点10分开始统计单车数据
     */
    @Scheduled(cron = "0 10 0 * * ?")
    public void EveryDayCanHandle(){

        this.iDeviceService.autoCanSignleStatis();
        this.iDeviceService.autoClacOnlineResult();
    }

    /**
     * 每天零点30分统计每辆车的CAN分析
     */
    @Scheduled(cron = "0 30 0 * * ?")
    public void EveryDayCanHistorys(){ this.iCanService.CalcDeviceCanHistorys();}

    /**
     * 每天一点计算不安全行为指数
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void EveryDayUnsafeIndex() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException { this.iUnSafeService.CalcUnsafeIndex();}

    /**
     * 每天两点计算昨天报警视频大小
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void EveryDayAlarmVideo(){ this.iFlowService.SensusAlarmVideo();}

    /**
     * 巡检ADAS状态
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void EveryDayADAS(){ this.iDeviceService.AutoInspectDeviceADAS();}

    /**
     * 每天零点10分开始统计不安全行为，行为识别，adas形成报表
     */
    @Scheduled(cron = "0 10 0 * * ?")
    public void EveryDayStatement() throws NoSuchFieldException, IllegalAccessException, IntrospectionException { this.iDeviceService.autoStatement();}
}
