package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramCanActinfo;
import com.sztvis.buscloud.model.domain.TramCanInfo;
import com.sztvis.buscloud.model.domain.TramUnsafeBehaviorInfo;
import com.sztvis.buscloud.model.dto.CanViewModel;
import com.sztvis.buscloud.model.dto.DispatchModel;
import com.sztvis.buscloud.model.dto.service.SaveAlarmQuery;

import java.util.List;
import java.util.Map;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/10 上午10:32
 */
public interface ICanService {
    /**
     * 根据编码和时间获得CAN信息
     * @param deviceCode
     * @param updateTime
     * @return
     */
    TramCanInfo GetCanInfo(String deviceCode,String updateTime);

    /**
     * 新增CAN数据
     * @param canInfo
     */
    void Save(TramCanInfo canInfo);

    /**
     * 更新CAN数据
     * @param canInfo
     */
    void Update(TramCanInfo canInfo);

    /**
     * 获得该车时间倒序最后一条can数据
     * @param deviceId
     * @return TramCanInfo
     */
    TramCanInfo FindLastById(long deviceId);

    /**
     * 获得该车时间倒序最后一条can数据
     * @param deviceCode
     * @return
     */
    TramCanInfo FindLastByCode(String deviceCode);

    /**
     * 新增报警数据
     * @param query
     */
    void AddAlarmInfo(SaveAlarmQuery query);

    /**
     * 插入CAN动作,先根据时间和编码查找can信息，存在就在can信息下面增加一个动作集合
     * @param updateTime 关联can信息的时间
     * @param deviceCode 设备编码
     * @param map 动作集合
     */
    void AddCanActInfo(String updateTime, String deviceCode, List<TramCanActinfo> map);

    /**
     * 获得车辆最后一条Can数据
     * @param deviceCode
     * @return
     */
    TramCanInfo getLastCanInfo(String deviceCode);

    TramCanInfo getLastCanInfo(String deviceCode,String updateTime);

    /**
     * 查询车辆最后刹车状态
     * @param deviceCode
     * @return
     */
    boolean IsBarke(String deviceCode);

    /**
     * 查询车辆最后停车挡状态
     * @param deviceCode
     * @return
     */
    boolean IsPark(String deviceCode);
    /**
     * 拼装报警条件
     * @param deviceCode  设备编码
     * @param updateTime  更新时间
     * @param type 报警类型
     * @param value 报警值
     * @param extras 附带数据 车速，车距，刹车
     * @param path 报警视频路径
     * @return
     */
    SaveAlarmQuery getAlarmQuery(String deviceCode,long deviceId,String updateTime,int type,String value,String extras,String path);

    /**
     * 获得最后一条can数据，包含can,can状态，调度，地图数据
     * @param devicecode
     * @return
     */
    CanViewModel getLastCanViewModel(String devicecode);

    /**
     * 获得最后一条调度数据
     * @param devicecode
     * @return
     */
    DispatchModel getLastDispathModel(String devicecode);

    /**
     * 统计不安全数据
     * @param deviceId
     */
    void autoCalcUnsafeData(long deviceId,String updateTime);

    /**
     * 增加不安全行为数据
     * @param behaviorInfo
     */
    void insertUnsafeData(TramUnsafeBehaviorInfo behaviorInfo);

    /**
     * 增加不安全行为数据
     * @param deviceId 设备id
     * @param updateTime 时间点
     * @param unsafeType 不安全类型
     */
    void insertUnSafeData(long deviceId,String updateTime,int unsafeType);

}