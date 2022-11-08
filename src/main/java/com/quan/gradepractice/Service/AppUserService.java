package com.quan.gradepractice.Service;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.quan.gradepractice.Entity.AppUser;

public interface AppUserService {
    AppUser RegisterUser(AppUser appUser);
    AppUser getUser(String userName);
    AppUser getUserById(Long id);
 
    void updateUser(String userName, String password);
    
    void updateToManager(String userName);
    void deleteUser(String userName);
    Set<AppUser> getUsers();

}
