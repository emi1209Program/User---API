package com.exercise.userapi.controller;

import com.exercise.userapi.model.LoginRequest;
import com.exercise.userapi.model.User;
import com.exercise.userapi.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {

        User user = userService.login(
                request.getTaxId(),
                request.getPassword()
        );

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(user);
    }
}