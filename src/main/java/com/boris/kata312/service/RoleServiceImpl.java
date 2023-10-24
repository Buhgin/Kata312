package com.boris.kata312.service;

import com.boris.kata312.dao.RoleDao;
import com.boris.kata312.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;
    @Override
    @Transactional
    public List<Role> listRole() {
        return roleDao.listRole();
    }
    @Transactional
    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    @Transactional
    public Role findByRoleName(String role) {
        return roleDao.findByRoleName(role);
    }
}
