package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.dto.push.PushModel;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/29 上午9:55
 */
public interface IPushService {

    void sendMsg(PushModel msg);

    void sendToUsers(List<String> users,PushModel msg);
}
