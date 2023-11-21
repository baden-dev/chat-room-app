package com.baden.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class ChatWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //This method configures the endpoint for WebSocket communication.
        // It sets up an endpoint at "/chatApp" and enables SockJS a JS library that provides a fallback for browsers that do not support webSocket
        registry.addEndpoint("/chatApp").withSockJS(); //ws
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //This method configures the message broker

        //Defines the prefix to use for messages that are bound for methods annotated with @MessageMapping
        registry.setApplicationDestinationPrefixes("/app");

        //Enables a simple memory-based message broker that routes messages with the specified prefix ("/topic" in this case) to the broker
        registry.enableSimpleBroker("/topic");
    }
}
