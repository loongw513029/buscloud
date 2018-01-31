package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramGpsInfo;
import com.sztvis.buscloud.model.dto.GpsViewModel;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/12 上午9:09
 */
public interface IGpsService {
    /**
     * 保存gps信息到mongoDb
     * @param gpsInfo
     */
    void saveGpsInfo(TramGpsInfo gpsInfo);

    /**
     * 得到最后一条数据
     * @param deviceCode 设备编码
     * @Param UpdateTime 时间点
     * @return
     */
    TramGpsInfo getLastGpsInfo(String deviceCode,String UpdateTime);

    /**
     * 得到最后一条数据
     * @param deviceCode
     * @return
     */
    TramGpsInfo getLastGpsInfo(String deviceCode);

    /**
     * 得到最后一条数据
     * @param deviceId
     * @return
     */
    TramGpsInfo getLastGpsInfo(long deviceId);
    /**
     * 获得AppGpsModel
     * @param deviceid
     * @return
     */
    GpsViewModel getAppGpsViewModel(long deviceid,String starttime);

    /**
     * 查询时间段内设备的gps记录条数
     * @param deviceid
     * @return
     */
    long getGpsCount(long deviceid,String starttime,String endtime);

    /**
     * 获得当前Gps状态
     * @param deviceid 设备id
     * @param starttime 开始监控时间
     * @param gpsState
     * @Param deviceCode
     * @return 1:正常 2:正常停车 3:正常正在运行有报警 4:正常停车有报警 5:正常但是gps故障 6:正常有故障有报警 7:离线
     */
    int getDeviceCurrentGpsState(long deviceid,String starttime,boolean gpsState,boolean onLineState,String deviceCode);


}
