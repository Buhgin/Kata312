package com.boris.kata312.controller;


import com.boris.kata312.model.Role;
import com.boris.kata312.model.User;
import com.boris.kata312.model.UserRole;
import com.boris.kata312.service.RoleService;
import com.boris.kata312.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    // Create
    @PostMapping("")
    public String createUser(@ModelAttribute("userNew") User user,
                             @RequestParam("roleIds") List<Long> roleIds) {

        userService.add(user, roleIds);
        return "redirect:/admin";
    }



    //Update
    @PostMapping("/{id}")
    public String edit(Model model, @ModelAttribute("user") User user, @PathVariable("id") long id,
                       @RequestParam(required = false) List<Long> roleIds) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roleList", userService.listUsers());

        userService.update(id, user, roleIds);
        return "redirect:/admin";
    }

    // Delete
    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


    @GetMapping()
    public String show(Model model,@AuthenticationPrincipal User admin) {
        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("userRoles", roleService.listRole());

        User user = new User();
        model.addAttribute("userNew", user);
        List<Role> roles = roleService.listRole();
        model.addAttribute("rolesNew", roles);
        return "admin";
    }

}