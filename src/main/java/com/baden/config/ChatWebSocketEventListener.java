package com.baden.config;

import com.baden.model.ChatMessage;
import com.baden.model.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class ChatWebSocketEventListener {

    private final SimpMessageSendingOperations messageSendingOperations;

    @Autowired
    public ChatWebSocketEventListener(SimpMessageSendingOperations messageSendingOperations) {
        this.messageSendingOperations = messageSendingOperations;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionDisconnectEvent event) {
        System.out.printf("User %s connected to the chat room.%n", retrieveUsername(event));
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageType(MessageType.LEAVE);
        chatMessage.setMessageSender(retrieveUsername(event));

        messageSendingOperations.convertAndSend("/topic/publicChat", chatMessage);
        System.out.printf("User %s disconnected.%n", chatMessage.getMessageSender());
    }

    public String retrieveUsername(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        return headerAccessor.getSessionAttributes().get("username").toString();
    }

}
