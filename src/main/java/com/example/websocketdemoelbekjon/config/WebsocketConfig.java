package com.example.websocketdemoelbekjon.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration    // class ni bean qilib object olish uchun ishlatiladi.
@EnableWebSocketMessageBroker
public class  WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
            registry.addEndpoint("/ws").withSockJS();   //web socketga ulanish uchun  kerak buladi
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
         registry.enableSimpleBroker("/topic");   // Kanalni ochib quyish va o'sha kanalga a'zo bo'lgan hamma kelgan message ni eshitadi
         registry.setApplicationDestinationPrefixes("/app");     // Clientdan turib serverga nimadir berishda kerak buladigan URl  ning prefix ti
    }
}


// Springda web socket bilan bog'liq hammaga configuratsiyta shu
