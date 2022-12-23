package com.example.webosmotic.controller;

import com.example.webosmotic.entity.MyUser;
import com.example.webosmotic.helper.JwtUtil;
import com.example.webosmotic.model.JwtRequest;
import com.example.webosmotic.model.JwtResponse;
import com.example.webosmotic.service.CustomUserDetailService;
import com.example.webosmotic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/v1")
@RestController
public class JwtController {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        } catch (BadCredentialsException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MyUser userDetails = (MyUser) customUserDetailService.loadUserByUsername(jwtRequest.getEmail());
        String token = jwtUtil.generateToken(userDetails);
        System.out.println("Bearer " + token);
        return ResponseEntity.ok(new JwtResponse(token));

    }
}
