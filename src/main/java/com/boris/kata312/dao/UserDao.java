package com.boris.kata312.dao;

import com.boris.kata312.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    void delete(Long id);
    void update(User user);
    User getById(Long id);
    boolean isExist(Long id);

    User findByEmail(String email);
}
