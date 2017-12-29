package com.sztvis.buscloud.service;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/29 上午9:55
 */
public interface RedisService {
    public void set(String key,Object value);
    public Object get(String key);
}
