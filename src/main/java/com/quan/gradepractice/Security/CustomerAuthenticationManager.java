package com.quan.gradepractice.Security;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.quan.gradepractice.Entity.AppUser;
import com.quan.gradepractice.Exception.EntityNotFoundException;
import com.quan.gradepractice.Repository.AppUserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomerAuthenticationManager implements AuthenticationManager {

   private AppUserRepository appUserRepository;
   private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        Optional<AppUser> entity = appUserRepository.findByUserName(authentication.getName());
        if(!entity.isPresent()) throw new EntityNotFoundException("the user name " + authentication.getName() + " not exists");

        AppUser user = entity.get();
       boolean isCorrect = bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword());
        if(!isCorrect) {
            throw new BadCredentialsException("you provide a wrong password");

        }
       Set<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getName().getRoleType())).collect(Collectors.toSet());
       return new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(), authorities);
    }
}
