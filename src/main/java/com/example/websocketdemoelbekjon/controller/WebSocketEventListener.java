package com.example.websocketdemoelbekjon.controller;


import com.example.websocketdemoelbekjon.entity.ChatMessage;
import com.example.websocketdemoelbekjon.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private  static  final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);


    @Autowired
    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public  void handleWebSocketConnectListener(SessionConnectEvent event){
        logger.info("Received a new web socked connection");
    }

    @EventListener
    public  void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());


        User username = (User)headerAccessor.getSessionAttributes().get("username");

    if (username != null){
        logger.info("User Disconnected: " + username);

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setFrom(username);

        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
    }

}
