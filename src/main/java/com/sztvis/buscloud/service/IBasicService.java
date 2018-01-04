package com.sztvis.buscloud.service;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午2:19
 */
public interface IBasicService {
    List<String> GetDeviceScopeByUserId(long userId);
}
