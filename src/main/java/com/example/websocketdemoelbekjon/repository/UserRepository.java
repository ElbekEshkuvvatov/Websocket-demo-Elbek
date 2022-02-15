package com.example.websocketdemoelbekjon.repository;

import com.example.websocketdemoelbekjon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


     boolean existsById(Integer id);

     boolean existsByEmail(String email);


}
