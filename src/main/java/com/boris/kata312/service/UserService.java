package com.boris.kata312.service;

import com.boris.kata312.model.User;
import com.boris.kata312.model.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void add(User user, List<Long> roleIds);
    List<User> listUsers();
    boolean delete(Long id);
    void update(long userId,User user, List<Long> roleIds);
    User getById(Long id);
    User getByEmail(String email);
    boolean isExistEmail(String email);

}
