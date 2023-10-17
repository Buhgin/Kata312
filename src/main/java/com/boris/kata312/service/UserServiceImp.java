package com.boris.kata312.service;


import com.boris.kata312.dao.UserDao;
import com.boris.kata312.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }


    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public void delete(Long id) {

        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        User user1 = userDao.getById(user.getId());
        user1.setEmail(user.getEmail());
        user1.setLastName(user.getLastName());
        user1.setFirstName(user.getFirstName());
        userDao.update(user);

    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }


}
