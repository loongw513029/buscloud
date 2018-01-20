package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.domain.TramDeviceHealthInfo;
import com.sztvis.buscloud.model.dto.DeviceViewModel;
import com.sztvis.buscloud.model.entity.DeviceStateFiled;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午6:30
 */
public interface IDeviceService {

    /**
     * 根据编码取得设备信息
     * @param deviceCode
     * @return
     */
    TramDeviceInfo getDeviceInfoByCode(String deviceCode);

    /**
     * 根据id获得设备信息
     * @param Id
     * @return
     */
    TramDeviceInfo getDeviceInfoById(long Id);
    /**
     * 根据线路获得设备列表
     * @param lineId
     * @return
     */
    List<TramDeviceInfo> GetDevicesByLineId(long lineId);

    /**
     * 增加心跳记录
     * @param healthInfo
     */
    void AddDeviceHealthInfo(TramDeviceHealthInfo healthInfo);

    /**
     * 修改设备状态
     * @param deviceCode
     * @param state
     */
    void UpdateDeviceStatus(String deviceCode,boolean state);

    /**
     * 修改实时巡检表数据，无该设备的巡检数据则新增一条
     * @param deviceCode
     * @param field
     * @param value
     */
    void UpdateRealTimeInspect(String deviceCode, DeviceStateFiled filed, Object value);

    /**
     * 获得设备列表
     * @param userId
     * @param devicetype
     * @param departmentId
     * @param lineId
     * @param status
     * @param keywords
     * @return
     */
    List<DeviceViewModel> getList(long userId,int devicetype,long departmentId,long lineId,int status,String keywords);
}
