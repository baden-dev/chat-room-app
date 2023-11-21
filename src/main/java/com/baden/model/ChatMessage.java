package com.baden.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    private MessageType messageType;
    private String messageContent;
    private String messageSender;

}
