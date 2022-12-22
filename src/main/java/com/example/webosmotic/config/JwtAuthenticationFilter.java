package com.example.webosmotic.config;

import com.example.webosmotic.entity.MyUser;
import com.example.webosmotic.helper.JwtUtil;
import com.example.webosmotic.service.CustomUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    private Long userId;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {

        String reqTokenHeader = req.getHeader("Authorization");
        String email = null;
        String jwtToken = null;
        if (reqTokenHeader != null) {
            if (reqTokenHeader.startsWith("Bearer")) {
                jwtToken = reqTokenHeader.substring(7);
                try {
                    // uid=jwtUtil.extractUserId(jwtToken);
                    email = jwtUtil.extractUsername(jwtToken);

                } catch (ExpiredJwtException e) {
                    e.printStackTrace();
                }
                //security
                MyUser userDetails = (MyUser) customUserDetailService.loadUserByUsername(email);
                // setUserId(userDetails.getId());
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                } else {
                    System.out.println("username is null get from token");
                }

            } else {

                System.out.println("token is not start with bearer");
            }
        } /*else {
            System.out.println("token is null");

        }*/
        filterChain.doFilter(req, resp);
    }
}
