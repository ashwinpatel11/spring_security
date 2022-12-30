package com.example.security.controller;

import com.example.security.entity.MyUser;
import com.example.security.service.CustomAuthenticationProvider;
import com.example.security.service.CustomUserDetailService;
import com.example.security.helper.JwtUtil;
import com.example.security.model.JwtRequest;
import com.example.security.model.JwtResponse;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    /*@Autowired
    private AuthenticationManager authenticationManager;*/
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest)  {
        Authentication authentication=null;

        authentication = customAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
        if (authentication != null) {
            MyUser myUser = (MyUser) customUserDetailService.loadUserByUsername(jwtRequest.getEmail());
            String token = jwtUtil.generateToken(myUser);
            System.out.println("Bearer " + token);
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
