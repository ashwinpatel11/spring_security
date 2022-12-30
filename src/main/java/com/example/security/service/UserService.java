package com.example.security.service;


import com.example.security.model.JwtRequest;
import com.example.security.dto.UserResponseDto;
import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponseDto addUser(User user) {
        User user1= userRepository.save(user);
        return userToDto(user1);
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }
    public String getLoginUser(JwtRequest jwtRequest) {
        User user= userRepository.findByEmail(jwtRequest.getEmail());

        boolean b= new BCryptPasswordEncoder().matches(jwtRequest.getPassword(), user.getPassword());
        System.out.println("pwd : "+b);

        if(b){
            System.out.println(user.getEmail()+"  "+user.getPassword());
            System.out.println("now login");
            return "login acces";
        }
        return null;

    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    private UserResponseDto userToDto(User user){
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setPid(user.getPid());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());
        return userResponseDto;

    }
    
}
