package com.example.security.service;

import com.example.security.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)  {
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = customUserDetailService.loadUserByUsername(authentication.getName());

        if (userDetails != null && bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials());
        }
        throw new BusinessException("password not correct");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
