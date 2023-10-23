package com.boris.kata312.controller;

import com.boris.kata312.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {


    @GetMapping()
    public ResponseEntity<User> show(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.notFound().build();   }

        return ResponseEntity.ok(user);
    }
}
