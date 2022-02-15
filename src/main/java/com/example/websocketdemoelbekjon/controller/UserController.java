package com.example.websocketdemoelbekjon.controller;


import com.example.websocketdemoelbekjon.DTO.UserDTO;
import com.example.websocketdemoelbekjon.entity.Response;
import com.example.websocketdemoelbekjon.service.UserService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RequestMapping(path = "/api/users")
@RestController
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    public Response addUser(@RequestBody UserDTO userDTO){
        return  userService.addUser(userDTO);
    }

    @GetMapping("getAll")
    public  Response read(){
        return userService.getAllUser();
    }

    @GetMapping("/getId/{id}")
    public Response readOne(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/put/{id}")
    public Response update(@PathVariable(name = "id")Integer userId, @RequestBody UserDTO userDTO){
        return userService.editUser(userId,userDTO);
    }

    @DeleteMapping("/delete/{Id}")
    public Response delete(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }


}
