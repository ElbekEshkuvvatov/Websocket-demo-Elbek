package com.example.websocketdemoelbekjon.controller;

import com.example.websocketdemoelbekjon.entity.Response;
import com.example.websocketdemoelbekjon.entity.Role;
import com.example.websocketdemoelbekjon.service.RoleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{role}")
public class RoleController {

    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public Response addRole(@RequestParam Role role){
        return roleService.addRole(role);
    }

    @GetMapping
    public  Response getRole(){
        return roleService.getRoles();
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable(name = "id") Integer roleId, @RequestBody Role role) {
        return roleService.update(roleId, role);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Integer id){
        return  roleService.delete(id);
    }

}
