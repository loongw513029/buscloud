package com.sztvis.buscloud.config;

import com.sztvis.buscloud.util.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/3 下午4:18
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(Constant.WebSocketBroadcastPath,Constant.P2pPushBasePath);
        registry.setApplicationDestinationPrefixes(Constant.P2pPushBasePath);
        registry.setUserDestinationPrefix(Constant.WebSocketPathPerfix);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint(Constant.WebSocketPath).withSockJS();
    }
}
