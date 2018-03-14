package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.AlarmMapper;
import com.sztvis.buscloud.model.dto.AlarmViewModel;
import com.sztvis.buscloud.model.dto.HomeAlarmViewModel;
import com.sztvis.buscloud.service.IAlarmService;
import com.sztvis.buscloud.service.IDepartmentService;
import com.sztvis.buscloud.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/23 下午3:40
 */
@Service
public class AlarmService implements IAlarmService{
    @Autowired
    private AlarmMapper alarmMapper;
    @Autowired
    private IDepartmentService iDepartmentService;
    @Autowired
    private IDeviceService iDeviceService;
    @Override
    public List<AlarmViewModel> getAlarmTableList(long userId, long departmentId, long lineId, long type1, long type2, String date1, String date2, String keywords) {
        List<Long> departmens = this.iDepartmentService.GetDepartmentIdsByUserId(userId);
        return this.alarmMapper.getAlarmTableList(departmens,departmentId,lineId,type1,type2,date1,date2,keywords);
    }

    @Override
    public List<AlarmViewModel> getMapAlarmTableList(String devices, String starttime) {
        return this.alarmMapper.getMapAlarmTableList(devices,starttime);
    }

    @Override
    public AlarmViewModel getAlarmViewModel(long id) {
        return this.alarmMapper.getAlarmViewModel(id);
    }

    @Override
    public List<HomeAlarmViewModel> getTop6HomePageAlarms(long userId) {
        List<Long> devices = this.iDeviceService.getDeviceIdsByUserId(userId);
        return this.alarmMapper.getTop6Alarms(devices);
    }
}
