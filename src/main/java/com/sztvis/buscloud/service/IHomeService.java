package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.dto.WelcomeModel;
import com.sztvis.buscloud.model.dto.WelcomeTrendModel;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/9 上午9:48
 */
public interface IHomeService {

    WelcomeModel GetWelcomeData(long userId);

    /**
     * 首页趋势数据
     * @return
     */
    WelcomeTrendModel getWelcomeTrendModels(long userId);
}
