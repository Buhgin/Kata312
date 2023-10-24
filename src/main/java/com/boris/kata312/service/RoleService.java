package com.boris.kata312.service;

import com.boris.kata312.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> listRole();
    Role findByRoleName(String role);
    Role getById(Long id);
    void addRole(Role role);
}
