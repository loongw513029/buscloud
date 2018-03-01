package com.sztvis.buscloud.config;


import com.sztvis.buscloud.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    }
}
