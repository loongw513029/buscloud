package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.model.dto.push.PushModel;
import com.sztvis.buscloud.service.IPushService;
import com.sztvis.buscloud.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/29 上午9:57
 */
@Service
public class PushService implements IPushService {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void sendMsg(PushModel msg) {
        this.template.convertAndSend(Constant.ProducerPath,msg);
    }

    @Override
    public void sendToUsers(List<String> users, PushModel msg) {
        users.forEach(user->{
            this.template.convertAndSendToUser(user,Constant.P2pPushPath,msg);
        });
    }
}
