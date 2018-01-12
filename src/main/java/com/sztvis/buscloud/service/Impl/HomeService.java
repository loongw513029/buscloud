package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.DateUtil;
import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.mapper.LineMapper;
import com.sztvis.buscloud.model.dto.WelcomeModel;
import com.sztvis.buscloud.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/9 上午9:49
 */
@Service
public class HomeService implements IHomeService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LineMapper lineMapper;

    @Override
    public WelcomeModel GetWelcomeData(long userId) {
        WelcomeModel model =new WelcomeModel();
        List<Long> departmentIds = departmentService.GetDepartmentIdsByUserId(userId);
        Integer deviceNum = deviceMapper.getDeviceCount(1,departmentIds);
        Integer onlineNum = deviceMapper.getDeviceCount(-1,departmentIds);
        model.setTotelNum(deviceNum);
        model.setOnlineNum(onlineNum);
        model.setLineNum(lineMapper.GetLineIdsByDepartmentIds(departmentIds));
        Integer todayNum = deviceMapper.getOnlinePrecent(departmentIds, DateUtil.GetSystemDate("yyyy-MM-dd",0));
        Integer fiveNum = deviceMapper.getOnlinePrecent(departmentIds,DateUtil.GetSystemDate("yyyy-MM-dd",-5));
        Double todayPrecent = Math.floor(todayNum/deviceNum),
                fivePrecent = Math.floor(fiveNum/deviceNum);
        model.setTodayPrecent(new Double(todayPrecent).intValue());
        model.setFiveDayPrecent(new Double(fivePrecent).byteValue());
        Integer unsafeNum = deviceMapper.getUnSafeCountByDepartmentIds(departmentIds,DateUtil.GetSystemDate("yyyy-MM-dd",0));
        model.setUnsafeNum(unsafeNum);

        return model;
    }
}
