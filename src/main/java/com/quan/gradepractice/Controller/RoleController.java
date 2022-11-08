package com.quan.gradepractice.Controller;

import java.util.Set;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.gradepractice.Entity.Role;
import com.quan.gradepractice.Repository.RoleRepository;
import com.quan.gradepractice.Service.RoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Set<Role>> getRoles() {
        return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    }

    @PostMapping("/")
     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> addRole(Role role) {
        roleService.addRole(role);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
