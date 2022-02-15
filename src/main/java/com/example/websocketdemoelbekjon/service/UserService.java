package com.example.websocketdemoelbekjon.service;


import com.example.websocketdemoelbekjon.DTO.UserDTO;
import com.example.websocketdemoelbekjon.entity.Response;
import com.example.websocketdemoelbekjon.entity.Role;
import com.example.websocketdemoelbekjon.entity.User;
import com.example.websocketdemoelbekjon.repository.RoleRepository;
import com.example.websocketdemoelbekjon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final RoleRepository rolerepository;

    public UserService(UserRepository userRepository, RoleRepository rolerepository) {
        this.userRepository = userRepository;
        this.rolerepository = rolerepository;
    }

   public Response addUser (UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail()))
            return  new Response("Such email already exists in system!", false);

       User user = new User();
       user.setFirstname(userDTO.getFirstname());
       user.setLastname(userDTO.getLastname());
       user.setUsername(userDTO.getUsername());
       user.setEmail(userDTO.getEmail());


       List<Role> roleList =  new ArrayList<>();

       List<Integer> roleIdList = userDTO.getRoleListId();
       for (Integer roleId : roleIdList) {
           Optional<Role> optionalRole = rolerepository.findById(roleId);
           optionalRole.ifPresent(roleList::add);


       }

       user.setRoleList(roleList);
       userRepository.save(user);
       return new Response("User added successfully", true, user);
   }

   public Response editUser(Integer id, UserDTO userDTO){
       Optional<User> optionalUser = userRepository.findById(id);
       if (!optionalUser.isPresent())
           return new Response("Such user id was not found! ", false);

       User user = optionalUser.get();
       user.setFirstname(userDTO.getFirstname());
       user.setLastname(userDTO.getLastname());
       user.setEmail(userDTO.getEmail());
       user.setUsername(userDTO.getUsername());

       userRepository.save(user);
       return new Response("User updated!", true);

   }

   public  Response deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.delete(userRepository.getById(id));
            return new Response("User deleted!" , true);
        } else {
            return new Response("Such user id was not found",false);
        }
   }

   public Response getAllUser(){
       List<User> userList = userRepository.findAll();
       if (userList.isEmpty())
           return new Response("Sorry database is Empty", false);
       return  new Response("Success! ", true, userList);
   }

   public Response getUserById(Integer id) {
       Optional<User> userOptional = userRepository.findById(id);
       return userOptional.map(user -> new Response("Success", true, user)).orElseGet(() -> new Response("Such user id was not found!", false));
   }


}
