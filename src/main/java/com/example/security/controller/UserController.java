package com.example.security.controller;


import com.example.security.dto.UserResponseDto;
import com.example.security.entity.User;
import com.example.security.model.JwtRequest;
import com.example.security.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User")
@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/testUser")
    public String test() {
        System.out.println("test hello");
        String name=null;
        name.length();
        return "hello";
    }


    @PostMapping("/addUser")
    public UserResponseDto addUser(@RequestBody @Valid User user) {
        String pwd=bCryptPasswordEncoder.encode(user.getPassword());
        System.out.println("pass : "+pwd);
       user.setPassword(pwd);
        return userService.addUser(user);
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "record deleted";
    }
    @PostMapping("/loginUser")
    public String loginUser(@RequestBody JwtRequest jwtRequest){
        return userService.getLoginUser(jwtRequest);
    }


}
