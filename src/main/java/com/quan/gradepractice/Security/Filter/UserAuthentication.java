package com.quan.gradepractice.Security.Filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.gradepractice.Entity.AppUser;
import com.quan.gradepractice.Security.CustomerAuthenticationManager;
import com.quan.gradepractice.Security.SecurityConstant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAuthentication extends UsernamePasswordAuthenticationFilter{
    
    CustomerAuthenticationManager customerAuthenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            AppUser user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());    
           return customerAuthenticationManager.authenticate(authentication);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        List<String> authorities = authResult.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.toList());
        String token = JWT.create()
        .withSubject(authResult.getName())
        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.Expiry_time))
        .withClaim("ROLES", authorities)
        .sign(Algorithm.HMAC512(SecurityConstant.Private_key));
        response.addHeader("Authorization", "Bearer " + token);
        
       
        
    }
}
