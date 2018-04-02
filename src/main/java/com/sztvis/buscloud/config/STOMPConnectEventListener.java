package com.sztvis.buscloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.List;
import java.util.Map;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/4/2 下午3:24
 * STOMP监听类
 * 用于session注册 以及key值获取
 */
public class STOMPConnectEventListener implements ApplicationListener<SessionConnectedEvent> {
    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        GenericMessage msg = (GenericMessage)sha.getHeader("simpConnectMessage");
        String agentId = ((List)((Map)msg.getHeaders().get("nativeHeaders")).get("login")).get(0).toString();
        //String agentId = sha.getNativeHeader("login").get(0).toString();
        String sessionId = sha.getSessionId();
        webAgentSessionRegistry.registerSessionId(agentId,sessionId);
    }
}
