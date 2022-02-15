package com.example.websocketdemoelbekjon.service;


import com.example.websocketdemoelbekjon.entity.Response;
import com.example.websocketdemoelbekjon.entity.Role;
import com.example.websocketdemoelbekjon.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;

    }

    public Response addRole(Role role) {

        Role role1 = new Role();
        role1.setDescription(role.getDescription());
        role1.setRoleName(role.getRoleName());

        roleRepository.save(role1);
        return new Response(" Saved", true);


    }

    public Response getRoles(){
        List<Role> roleRepositoryAll = roleRepository.findAll();
        if (roleRepositoryAll.isEmpty())
            return new Response("Database hasn't things", false);
        return new Response("Success!", true, roleRepositoryAll);
    }




    public  Response update(Integer roleId, Role role){
        if (roleRepository.existsById(roleId)){

            Optional<Role> optionalRole = roleRepository.findById(roleId);

            if (optionalRole.isPresent()){
                Role role1 = optionalRole.get();
                role1.setRoleName(role.getRoleName());
                role1.setDescription(role.getDescription());
                roleRepository.save(role1);
                return new Response("Save", true);
            }
        }
        return new Response("Such role id was not found", false);
    }

    public Response delete(Integer id) {
        if (roleRepository.existsById(id)){
            roleRepository.deleteById(id);

           return  new Response("Role deleted", true);


        }
        return new Response("Such role id was not found", false);
    }

}
