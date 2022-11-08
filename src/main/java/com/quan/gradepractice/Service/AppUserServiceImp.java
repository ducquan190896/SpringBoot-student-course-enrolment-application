package com.quan.gradepractice.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quan.gradepractice.Entity.AppUser;
import com.quan.gradepractice.Entity.Role;
import com.quan.gradepractice.Entity.RoleType;
import com.quan.gradepractice.Exception.EntityNotFoundException;
import com.quan.gradepractice.Repository.AppUserRepository;
import com.quan.gradepractice.Repository.RoleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AppUserServiceImp implements AppUserService, UserDetailsService{

    AppUserRepository appUserRepository;
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> entity = appUserRepository.findByUserName(username);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the username " + username + " not found");
        }
        AppUser user = entity.get();

        User userDetails = new User(user.getUserName(), user.getPassword(), user.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getName().getRoleType())).collect(Collectors.toSet()));
        return userDetails;
    }

    @Override
    public void deleteUser(String userName) {
        
        Optional<AppUser> entity = appUserRepository.findByUserName(userName);
        AppUser user = checkUser(entity);
        appUserRepository.delete(user);
    }

    @Override
    public AppUser getUser(String userName) {
        
       Optional<AppUser> entity = appUserRepository.findByUserName(userName);
       AppUser user = checkUser(entity);
       System.out.println("hello");
       return user;
    }

    @Override
    public Set<AppUser> getUsers() {
        
        return appUserRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public AppUser RegisterUser(AppUser appUser) {

        appUser.getRoles().add(roleRepository.findByName(RoleType.ROLE_USER).get());
       
        return appUserRepository.save(appUser);
    }

    
    @Override
    public void updateUser(String userName, String password) {
        
        Optional<AppUser> entity = appUserRepository.findByUserName(userName);
       AppUser user = checkUser(entity);
       user.setPassword(new BCryptPasswordEncoder().encode(password));
       appUserRepository.save(user);
    }

  
    @Override
    public void updateToManager(String userName) {
        Optional<AppUser> entity = appUserRepository.findByUserName(userName);
        AppUser user = checkUser(entity);
        user.getRoles().add(roleRepository.findByName(RoleType.ROLE_MANAGER).get());
        appUserRepository.save(user);
        
    }

    private AppUser checkUser(Optional<AppUser> entity) {
        if(entity.isPresent()) {
            return entity.get();

        }
        throw new EntityNotFoundException("the user name is not found ");
    }

    @Override
    public AppUser getUserById(Long id) {
      
        Optional<AppUser> entity = appUserRepository.findById(id);
        AppUser user = checkUser(entity);
        return user;
    }
    
}
