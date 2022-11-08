package com.quan.gradepractice.Security.Filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quan.gradepractice.Security.SecurityConstant;

import lombok.AllArgsConstructor;


public class JwtAuthorization extends OncePerRequestFilter{
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        System.out.println(header);
        if(header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
          return;  
        } 
        String token = header.replace("Bearer ", "");
        System.out.println(token);
        DecodedJWT decodeJWT = JWT.require(Algorithm.HMAC512(SecurityConstant.Private_key)).build().verify(token);
        String username = decodeJWT.getSubject();
        String[] claims = decodeJWT.getClaim("ROLES").asArray(String.class);

        Set<SimpleGrantedAuthority> authorities = Arrays.stream(claims).map(auth -> new SimpleGrantedAuthority(auth)).collect(Collectors.toSet());

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
