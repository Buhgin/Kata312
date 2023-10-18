package com.boris.kata312.controller;


import com.boris.kata312.model.User;

import com.boris.kata312.model.UserRole;
import com.boris.kata312.service.RoleService;
import com.boris.kata312.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    @GetMapping()
    public String showUsersPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("rolesList", roleService.listRole());
        return "list-users";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("firstName") String firstName,
                          @RequestParam("lastName") String lastName,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password,
                          @RequestParam("roles") List<Long> roleIds,
                          RedirectAttributes redirectAttributes) {
        userService.add(firstName, lastName, email, password, roleIds);
        redirectAttributes.addFlashAttribute("successMessage", "User added successfully!");
        return "redirect:/admin";
    }

    @PostMapping("/remove")
    public String removeUser(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        userService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "User removed successfully!");
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("email") String email, RedirectAttributes redirectAttributes,
                             @RequestParam("roles") List<Long> roleIds,
                             @RequestParam("password") String password) {


        userService.update(id, firstName, lastName, email, password, roleIds);

        redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
        return "redirect:/admin";
    }

}