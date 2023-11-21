package com.baden.controller;

import com.baden.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage") //Invokes method when message with this specific destination is received
    @SendTo("/topic/publicChat") //The destination in which the return value will be sent
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/publicChat")
    public ChatMessage newUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        //modify header information of the webSocket session by adding "username" attribute so the websocket session so that it is associated with new user
        headerAccessor.getSessionAttributes().put("username", chatMessage.getMessageSender());
        return chatMessage;
    }

}
