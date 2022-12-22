package com.example.webosmotic.controller;


import com.example.webosmotic.entity.User;
import com.example.webosmotic.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Api(tags="User")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        String name=null;
        name.length();
        return "hello";
    }


    @PostMapping("/addUser")
    public User addUser(@RequestBody @Valid User user){
        return userService.addUser(user);
    }
    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable Long id){
       return userService.getUserById(id);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }
    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
         return "record deleted";
    }


}
