package com.boris.kata312.service;

import com.boris.kata312.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void delete(Long id);
    void update(User user);
    User getById(Long id);

}
