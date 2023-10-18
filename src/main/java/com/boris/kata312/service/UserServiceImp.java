package com.boris.kata312.service;


import com.boris.kata312.dao.RoleDao;
import com.boris.kata312.dao.UserDao;
import com.boris.kata312.model.Role;
import com.boris.kata312.model.User;
import com.boris.kata312.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserDao userDao;
    private final RoleDao roleDao;


    @Transactional
    @Override
    public void add(String firstName, String lastName, String email, String password, List<Long> role) {
       User newUser = new User();
         newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(bCryptPasswordEncoder.encode(password));
        Set<Role> roles = new HashSet<>();
        role.forEach(r -> roles.add(roleDao.getById(r)));
        newUser.setRoles(roles);
        userDao.add(newUser);
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
    public void update(Long id, String firstName, String lastName, String email, String password, List<Long> role) {
        User user1 = userDao.getById(id);
        user1.setEmail(email);
        user1.setLastName(lastName);
        user1.setFirstName(firstName);
        user1.setPassword(bCryptPasswordEncoder.encode(password));
        Set<Role> roles = new HashSet<>();
        role.forEach(r -> roles.add(roleDao.getById(r)));
        user1.setRoles(roles);
        userDao.update(user1);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
