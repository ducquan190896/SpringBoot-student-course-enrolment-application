package com.quan.gradepractice.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quan.gradepractice.Entity.Role;
import com.quan.gradepractice.Exception.EntityNotFoundException;
import com.quan.gradepractice.Repository.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService {
    
    RoleRepository roleRepository;
    
    
    @Override
    public void addRole(Role role) {
        Optional<Role> entity = roleRepository.findByName(role.getName());
        if(entity.isPresent()) {
            throw new EntityNotFoundException("the role with name " + role.getName().name() + " is already exists");
        }
        roleRepository.save(role);
        
    }

    @Override
    public Set<Role> getRoles() {
    
        return roleRepository.findAll().stream().collect(Collectors.toSet());
    }
    
    
}
