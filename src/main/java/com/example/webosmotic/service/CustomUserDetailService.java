package com.example.webosmotic.service;

import com.example.webosmotic.entity.MyUser;
import com.example.webosmotic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) {

       User user = userService.getUser(email);

        if (user !=null) {
            return new MyUser(user.getPid(),user.getEmail(),user.getPassword(),new ArrayList<>());
        } else {
                 throw new BadCredentialsException("user not found");

            }


    }
}
