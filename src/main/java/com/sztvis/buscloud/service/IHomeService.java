package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.dto.WelcomeModel;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/9 上午9:48
 */
public interface IHomeService {

    WelcomeModel GetWelcomeData(long userId);
}
