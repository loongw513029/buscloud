package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.domain.TramDeviceHealthInfo;
import com.sztvis.buscloud.model.domain.Tramdevicestateinspectrealtimeinfo;
import com.sztvis.buscloud.model.dto.DeviceViewModel;
import com.sztvis.buscloud.model.entity.DeviceStateFiled;
import com.sztvis.buscloud.service.IDepartmentService;
import com.sztvis.buscloud.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午6:30
 */

@Service
public class DeviceService implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private IDepartmentService iDepartmentService;

    @Override
    public TramDeviceInfo getDeviceInfoByCode(String deviceCode) {

        return this.deviceMapper.getDeviceInfoByCode(deviceCode);
    }

    @Override
    public TramDeviceInfo getDeviceInfoById(long Id) {

        return this.deviceMapper.getDeviceInfoById(Id);
    }

    @Override
    public List<TramDeviceInfo> GetDevicesByLineId(long lineId) {
        return deviceMapper.getDevicesByLineId(lineId);
    }

    @Override
    public void AddDeviceHealthInfo(TramDeviceHealthInfo healthInfo) {
        this.mongoTemplate.save(healthInfo);
        //有心跳表示在线，下面改变设备的状态
        this.UpdateDeviceStatus(healthInfo.getDevicecode(),true);
    }

    @Override
    public void UpdateDeviceStatus(String deviceCode, boolean state) {
        int v = state?1:-1;
        this.deviceMapper.udpateDeviceState(deviceCode,v);
        this.UpdateRealTimeInspect(deviceCode,DeviceStateFiled.OnlineState,true);
    }

    @Override
    public void UpdateRealTimeInspect(String deviceCode, DeviceStateFiled filed, Object value) {
        TramDeviceInfo deviceInfo = this.getDeviceInfoByCode(deviceCode);
        int count = this.deviceMapper.getRealtimeInspectCount(deviceCode);
        if(count == 0){
            Tramdevicestateinspectrealtimeinfo info = new Tramdevicestateinspectrealtimeinfo();
            info.setDeviceid(deviceInfo.getId());
            info.setDeviceCode(deviceInfo.getDevicecode());
            this.deviceMapper.insertRealtimeInspect(info);
        }
        this.deviceMapper.updateRealtimeInspect(deviceCode,filed.getValue(),value);
    }

    @Override
    public List<DeviceViewModel> getList(long userId, int devicetype, long departmentId, long lineId, int status, String keywords) {

        List<Long> departments = this.iDepartmentService.GetDepartmentIdsByUserId(userId);
        return this.deviceMapper.getBusList(departments,devicetype,departmentId,lineId,status,keywords);
    }


}
