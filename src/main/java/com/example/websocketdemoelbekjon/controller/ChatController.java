package com.example.websocketdemoelbekjon.controller;

import com.example.websocketdemoelbekjon.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public  ChatMessage senMessage(@Payload ChatMessage chatMessage) {
        return  chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor){
        // Add username in web Socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return  chatMessage;
    }

}
