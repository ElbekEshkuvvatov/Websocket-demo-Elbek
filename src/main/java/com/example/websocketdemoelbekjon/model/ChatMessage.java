package com.example.websocketdemoelbekjon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private MessageType type;
    private  String content;
    private  String sender;



    public  enum MessageType{
        CHAT,
        JOIN,
        LEAVE
    }

}
