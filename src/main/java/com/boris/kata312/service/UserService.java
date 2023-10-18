package com.boris.kata312.service;

import com.boris.kata312.model.User;
import com.boris.kata312.model.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void add(String firstName, String lastName, String email, String password, List<Long> role);
    List<User> listUsers();
    void delete(Long id);
    void update(Long id, String firstName, String lastName, String email, String password, List<Long> role);
    User getById(Long id);

}
