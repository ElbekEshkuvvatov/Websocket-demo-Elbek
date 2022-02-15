package com.example.websocketdemoelbekjon.service;


import com.example.websocketdemoelbekjon.DTO.ChatMessageDTO;
import com.example.websocketdemoelbekjon.entity.ChatMessage;
import com.example.websocketdemoelbekjon.entity.Response;
import com.example.websocketdemoelbekjon.entity.User;
import com.example.websocketdemoelbekjon.repository.ChatMessageRepository;
import com.example.websocketdemoelbekjon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {

    final ChatMessageRepository chatMessageRepository;
    final UserRepository userRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository, UserRepository userRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
    }

        public Response create(ChatMessageDTO chatMessageDTO){

            Optional<User> optionalUser = userRepository.findById(chatMessageDTO.getUserToId());
            User userToId = optionalUser.get();

            Optional<User> optional = userRepository.findById(chatMessageDTO.getUserFromId());
            User userFromId = optional.get();


            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setTo(userToId);
            chatMessage.setFrom(userFromId);
            chatMessage.setMessageTime(chatMessageDTO.getMessageTime());
            chatMessage.setMessage(chatMessage.getMessage());

         //   chatMessage.setSeen(chatMessageDTO.isSeen());    // To User ko'rganda from user uni bilishi kerak


            chatMessageRepository.save(chatMessage);
            return new Response("Operation well done", true);

        }

        public Response update(Integer id, ChatMessageDTO chatMessageDTO){

        if (chatMessageRepository.existsById(id)){
            Optional<ChatMessage> optionalChatMessage = chatMessageRepository.findById(id);

            Optional<User> userOptional = userRepository.findById(chatMessageDTO.getUserFromId());
            User user = userOptional.get();

            if (optionalChatMessage.isPresent()){
                ChatMessage chatMessage = optionalChatMessage.get();
                chatMessage.setMessage(chatMessageDTO.getMessage());
                 chatMessage.setFrom(user);
                chatMessageRepository.save(chatMessage);
                return  new Response("Success", true);
            }

        }

            return  new Response("Sorry such chatMessage id was not found", false);
        }

        public Response  getAll() {
            List<ChatMessage> messageList = chatMessageRepository.findAll();
            if (messageList.isEmpty())
                return new Response("Database is empty", false);

            return new Response("Database ", true, messageList);

        }

        public Response delete(Integer id) {

         if (chatMessageRepository.existsById(id)){
            chatMessageRepository.deleteById(id);

            return new Response("Deleted", true);

        }

            return new Response("Not Deleted", false);
        }

}
