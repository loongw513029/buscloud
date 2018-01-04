package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.dto.CurrentUserInfo;
import com.sztvis.buscloud.model.dto.LoginParams;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午2:17
 */
public interface IMemberService {
    CurrentUserInfo Login(LoginParams loginParams);
}
