package com.boris.kata312;

import com.boris.kata312.model.Role;
import com.boris.kata312.model.User;
import com.boris.kata312.model.UserRole;
import com.boris.kata312.service.RoleService;
import com.boris.kata312.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CommandInitClass implements CommandLineRunner {


    private final UserService userService;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    public CommandInitClass(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args)  {
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

        User admin = new User("Rex","rex","rex@mail.ru",
                passwordEncoder.encode("password"));
        admin.setRoles(setAdmin);
        User user = new User("Ivan", "Ivanov", "ivanov@mail.ru",
                passwordEncoder.encode("password"));
        user.setRoles(setUser);

        userService.add(admin, adminROLE);
        userService.add(user, userROLE);


    }
}