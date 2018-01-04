package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.model.domain.Tramdeviceinfo;
import com.sztvis.buscloud.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<Tramdeviceinfo> GetDevicesByLineId(long lineId) {

        return deviceMapper.GetDevicesByLineId(lineId);
    }
}
