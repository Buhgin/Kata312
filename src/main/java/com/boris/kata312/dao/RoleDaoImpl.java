package com.boris.kata312.dao;

import com.boris.kata312.model.Role;
import com.boris.kata312.model.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findByRoleName(String role) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.role = :role", Role.class)
                .setParameter("role", role)
                .getSingleResult();
    }

    @Override
    public List<Role> listRole() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class)
                .getResultList();
    }
}
