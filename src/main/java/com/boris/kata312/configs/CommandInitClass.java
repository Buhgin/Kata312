package com.boris.kata312.configs;

import com.boris.kata312.model.Role;
import com.boris.kata312.model.User;
import com.boris.kata312.model.UserRole;
import com.boris.kata312.service.RoleService;
import com.boris.kata312.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CommandInitClass implements CommandLineRunner {


    private final UserService userService;
    private final RoleService roleService;



    @Override
    public void run(String... args) {
        Role roleAdmin = new Role();
        roleAdmin.setRole(UserRole.ROLE_ADMIN);
        Role roleUser = new Role();
        roleUser.setRole(UserRole.ROLE_USER);

        if (roleService.listRole().isEmpty()) {

            roleService.addRole(roleAdmin);
            roleService.addRole(roleUser);
        }
        Set<Role> setAdmin = new HashSet<>();
        Set<Role> setUser = new HashSet<>();

        List<Long> adminROLE = new ArrayList<>();
        adminROLE.add(1L);
        adminROLE.add(2L);

        List<Long> userROLE = new ArrayList<>();
        userROLE.add(2L);


        setAdmin.add(roleAdmin);
        setAdmin.add(roleUser);
        setUser.add(roleUser);

        User admin = new User("Rex", "rex", "rex@mail.ru",
                "password");
        admin.setRoles(setAdmin);
        User user = new User("Ivan", "Ivanov", "ivanov@mail.ru",
                "password");
        user.setRoles(setUser);


        if (userService.isExistEmail(admin.getEmail()) && userService.isExistEmail(user.getEmail())) {
            userService.delete(userService.getByEmail(admin.getEmail()).getId());
            userService.delete(userService.getByEmail(user.getEmail()).getId());
            userService.add(admin, adminROLE);
            userService.add(user, userROLE);
        }


    }
}