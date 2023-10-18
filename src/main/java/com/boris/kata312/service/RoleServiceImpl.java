package com.boris.kata312.service;

import com.boris.kata312.dao.RoleDao;
import com.boris.kata312.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;
    @Override
    public List<Role> listRole() {
        return roleDao.listRole();
    }
}
