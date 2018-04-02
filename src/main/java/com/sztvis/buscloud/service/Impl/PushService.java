package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.config.SocketSessionRegistry;
import com.sztvis.buscloud.model.dto.push.PushModel;
import com.sztvis.buscloud.service.IMemberService;
import com.sztvis.buscloud.service.IPushService;
import com.sztvis.buscloud.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/29 上午9:57
 */
@Service
public class PushService implements IPushService {
    //@Autowired
    //SocketSessionRegistry socketSessionRegistry;

    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private IMemberService iMemberService;


    @Override
    public void sendMsg(PushModel msg) {
        this.template.convertAndSend(Constant.ProducerPath,msg);
    }

    @Override
    public void sendToUsers(List<String> users, PushModel msg) {
        users.forEach(x->{
                template.convertAndSendToUser(x, Constant.P2pPushPath, msg);
        });
//        List<String> keys = socketSessionRegistry.getAllSessionIds().entrySet()
//                .stream().map(Map.Entry::getKey)
//                .collect(Collectors.toList());
//        users.forEach(u -> {
//            if (keys.contains(u)) {
//                String sessionId = socketSessionRegistry.getSessionIds(u).stream().findFirst().get().toString();
//                template.convertAndSendToUser(sessionId, Constant.P2pPushPath, msg);
//            }
//        });
    }

    @Override
    public void SendToMsgByDeviceCode(String deviceCode, PushModel msg) {
        List<String> uuids = this.iMemberService.getMemberUUIDByDeviceCode(deviceCode);
        this.sendToUsers(uuids,msg);
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
