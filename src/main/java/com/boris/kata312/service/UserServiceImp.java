package com.boris.kata312.service;


import com.boris.kata312.dao.RoleDao;
import com.boris.kata312.dao.UserDao;
import com.boris.kata312.model.Role;
import com.boris.kata312.model.User;
import com.boris.kata312.model.UserRole;
import com.boris.kata312.payload.UserUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserDao userDao;
    private final RoleDao roleDao;


    @Transactional
    @Override
    public void add(User user,List<Long> roleIds) {
       User newUser = new User();
         newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = roleIds.stream()
                .map(roleDao::getById)
                .collect(Collectors.toSet());
        newUser.setRoles(roleSet);
        userDao.add(newUser);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        userDao.delete(id);

    return userDao.isExist(id);}
    @Transactional
    @Override
    public User getByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean isExistEmail(String email) {
        return userDao.isExistEmail(email);
    }

    @Transactional
    @Override
    public void update(long id, UserUpdate user, List<Long> roleIds) {
        User user1 = userDao.getById(id);
        user1.setEmail(user.email());
        user1.setFirstName(user.firstName());
        user1.setLastName(user.lastName());
        if(!user.password().isEmpty()){
            user1.setPassword(bCryptPasswordEncoder.encode(user.password()));
        }

        if(!roleIds.isEmpty()){
            Set<Role> roleSet = roleIds.stream()
                    .map(roleDao::getById)
                    .collect(Collectors.toSet());
            user1.setRoles(roleSet);
        }

        userDao.update(user1);
    }

    @Override
    @Transactional
    public User getById(Long id) {
        return userDao.getById(id);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
