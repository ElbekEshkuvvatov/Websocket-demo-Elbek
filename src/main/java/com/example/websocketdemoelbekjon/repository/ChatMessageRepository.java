package com.example.websocketdemoelbekjon.repository;

import com.example.websocketdemoelbekjon.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {


    boolean existsById(Integer integer);
}
