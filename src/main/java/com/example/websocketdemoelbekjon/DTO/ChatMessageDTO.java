package com.example.websocketdemoelbekjon.DTO;


import com.example.websocketdemoelbekjon.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessageDTO {

    private  String message;
    private Timestamp messageTime;
    private Integer userFromId;
    private  Integer userToId;
    private boolean seen;

}
