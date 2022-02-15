package com.example.websocketdemoelbekjon.repository;

import com.example.websocketdemoelbekjon.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {


    boolean existsById(Integer integer);
}
