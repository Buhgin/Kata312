package com.boris.kata312.controller;


import com.boris.kata312.model.Role;
import com.boris.kata312.model.User;
import com.boris.kata312.payload.ResponseAdmin;
import com.boris.kata312.service.RoleService;
import com.boris.kata312.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @PostMapping()
    public void createUser(@RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("email") String email,
                            @RequestParam("password") String password,
                           @RequestParam("roleIds") List<Long> roleIds) {

        userService.add(new User(firstName,lastName,email,password), roleIds);

    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(roleService.listRole());
    }



    @PutMapping("/{id}")
    public void edit(Model model, @ModelAttribute("user") User user, @PathVariable("id") long id,

                     @RequestParam List<Long> roleIds) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roleList", userService.listUsers());

        userService.update(id, user, roleIds);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {

       if (userService.delete(id)) {
            return ResponseEntity.ok().build();
        }
     return ResponseEntity.noContent().build();

    }


    @GetMapping()
    public ResponseEntity<ResponseAdmin> show(@AuthenticationPrincipal User admin) {

        ResponseAdmin responseAdmin = new ResponseAdmin(userService.listUsers(),
                roleService.listRole(), admin, new User(), roleService.listRole());
        return ResponseEntity.ok(responseAdmin);
    }

}