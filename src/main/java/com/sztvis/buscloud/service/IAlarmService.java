package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.dto.AlarmViewModel;
import com.sztvis.buscloud.model.dto.HomeAlarmViewModel;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/23 下午3:34
 */
public interface IAlarmService {
    List<AlarmViewModel> getAlarmTableList(long userId,long departmentId,long lineId,long type1,long type2,String date1,String date2,String keywords);

    List<AlarmViewModel> getMapAlarmTableList(String devices,String starttime);

    AlarmViewModel getAlarmViewModel(long id);

    int getAlarmTrendsCounts(long userId,String startTime,String endTime,int alarmType);

    List<HomeAlarmViewModel> getTop6HomePageAlarms(long userId);
}
