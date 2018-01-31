package com.sztvis.buscloud.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * 定时巡检设备在线状态,每分钟查询巡检表然后送推到页面上
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void IntervalOnlineTask(){

        System.out.println("定时器..");
    }
}
