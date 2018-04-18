package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.HomealramViewModel;
import com.sztvis.buscloud.model.dto.*;
import com.sztvis.buscloud.model.dto.api.AppAlarmChartModel;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/23 下午3:34
 */
public interface IAlarmService {
    List<AlarmViewModel> getAlarmTableList(long userId,long departmentId,long lineId,long type1,long type2,String date1,String date2,String keywords,int offest,int limit);
    int getAlarmTableListCount(long userId,long departmentId,long lineId,long type1,long type2,String date1,String date2,String keywords);

    List<AlarmViewModel> getMapAlarmTableList(String devices,String starttime);

    AlarmViewModel getAlarmViewModel(long id);

    int getAlarmTrendsCounts(long userId,String startTime,String endTime,int alarmType);

    List<HomeAlarmViewModel> getTop6HomePageAlarms(long userId);

    List<AlarmViewModel> GetList(long userId, int dayType, long typeId, long alarmType, String code, long lineId);

    CanAlarmInfo GetCanAlarmInfo(int Id);

    BicycleViewModel GetBicycleUnSafeInfo(int dayType, long deviceId);

    AppAlarmChartModel GetAppAlarmCharts(long userId, long lineId, int type);

    HomealramViewModel.ViewModel GetTop10Alarms(long userId);

    WelcomeTrendModel getAlarmChartList(long lineId,int type ,String type1,String date2,String date3, String code,long departmentId);
}
