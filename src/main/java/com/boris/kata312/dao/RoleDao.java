package com.boris.kata312.dao;

import com.boris.kata312.model.Role;

import java.util.List;

public interface RoleDao {
    Role getById(Long id);

    Role findByRoleName(String role);

    List<Role> listRole();

    void addRole(Role role);

}
