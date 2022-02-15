package com.example.websocketdemoelbekjon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Column(nullable = false)
    private  String message;

    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("now()")
    private Date messageTime;

    @JoinColumn(name = "from_id")
    @ManyToOne
    private  User from;

    @JoinColumn(name = "to_id")
    @ManyToOne
    private  User to;









}
