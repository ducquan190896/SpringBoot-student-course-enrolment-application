package com.quan.gradepractice.Controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.gradepractice.Entity.AppUser;
import com.quan.gradepractice.Service.AppUserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("ap1/v1/users")
public class AppUserController {
    AppUserService appUserService;

    @GetMapping("/{userName}")
    public ResponseEntity<AppUser> getUserByUserName(@PathVariable String userName) {
        return new ResponseEntity<AppUser>(appUserService.getUser(userName), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        return new ResponseEntity<AppUser>(appUserService.getUserById(id), HttpStatus.OK);
    }
    @PostMapping("/registration")
    // @PreAuthorize("permitAll")
    public ResponseEntity<AppUser> saveUser(@Valid @RequestBody AppUser appUser) {
        System.out.println("hello");
        return new ResponseEntity<AppUser>(appUserService.RegisterUser(appUser), HttpStatus.CREATED);
    }
    @DeleteMapping("/{userName}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String userName) {
        appUserService.deleteUser(userName);
        return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }
    @PutMapping("/userName/{userName}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable String userName, @RequestBody String password) {
        appUserService.updateUser(userName, password);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping("/updateManager/userName/{userName}")
    public ResponseEntity<HttpStatus> updateUserToManager(@PathVariable String userName, @RequestBody String password) {
        appUserService.updateToManager(userName);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
