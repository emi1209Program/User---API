package com.exercise.userapi.controller;

import com.exercise.userapi.model.User;
import com.exercise.userapi.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * REST Controller that exposes user endpoints.
 *
 * Endpoints:
 * GET /users
 * POST /users
 * PATCH /users/{id}
 * DELETE /users/{id}
 */

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET /users
     * Supports sorting and filtering
     */
    @GetMapping
    public List<User> getUsers(
            @RequestParam(required = false) String sortedBy,
            @RequestParam(required = false) String filter) {

        return userService.getUsers(sortedBy, filter);
    }

    /**
     * POST /users
     * Creates a new user
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {

        try {

            User createdUser = userService.createUser(user);

            return ResponseEntity.ok(createdUser);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    /**
     * PATCH /users/{id}
     * Updates user attributes
     */
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable UUID id,
            @RequestBody Map<String, Object> updates) {

        User updatedUser = userService.updateUser(id, updates);

        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedUser);
    }

    /**
     * DELETE /users/{id}
     * Deletes a user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {

        boolean deleted = userService.deleteUser(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}