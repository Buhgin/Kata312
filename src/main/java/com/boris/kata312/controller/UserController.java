package com.boris.kata312.controller;

import com.boris.kata312.model.User;
import com.boris.kata312.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public String showUsersPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.listUsers());
        return "list-users";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        userService.add(user);
        redirectAttributes.addFlashAttribute("successMessage", "User added successfully!");
        return "redirect:/users";
    }

    @PostMapping("/remove")
    public String removeUser(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        userService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "User removed successfully!");
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser( @RequestParam("id") Long id,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        userService.update(new User(id,firstName,lastName,email));
        redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
        return "redirect:/users";
    }

}