package com.quan.gradepractice.Service;

import java.util.Set;

import com.quan.gradepractice.Entity.Role;

public interface RoleService {
    void addRole(Role role);
    Set<Role> getRoles();

}
