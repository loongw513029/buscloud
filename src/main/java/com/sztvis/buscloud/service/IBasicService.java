package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramBasicInfo;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午2:19
 */
public interface IBasicService {
    /**
     * 根据用户获得该用户所管辖设备列表集合
     * @param userId
     * @return
     */
    List<String> GetDeviceScopeByUserId(long userId);

    /**
     * 获得报警或者动作集合对应的主机上传自定义Id集合
     * @param type
     * @return
     */
    List<Integer> getCustomIdsByType(int type) throws Exception;

    /**
     * 根据主机自定义Id获得基础类
     * @param customId
     * @return
     */
    TramBasicInfo getBasicInfoByCustomId(int customId);
}
