package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramGpsInfo;

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
     * @param deviceCode
     * @return
     */
    TramGpsInfo getLastGpsInfo(String deviceCode,String UpdateTime);
}
