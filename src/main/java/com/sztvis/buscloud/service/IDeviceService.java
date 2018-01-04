package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.Tramdeviceinfo;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午6:30
 */
public interface IDeviceService {

    List<Tramdeviceinfo> GetDevicesByLineId(long lineId);
}
