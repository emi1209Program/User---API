package com.exercise.userapi.controller;

import com.exercise.userapi.model.User;
import com.exercise.userapi.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST que expone los endpoints relacionados a usuarios.
 *
 * REST Controller that exposes user-related endpoints.
 *
 * Este controlador permite:
 *
 * - Consultar usuarios (GET /users)
 * - Crear usuarios (POST /users)
 * - Actualizar usuarios (PATCH /users/{id})
 * - Eliminar usuarios (DELETE /users/{id})
 *
 * Los usuarios se almacenan en memoria usando un ArrayList
 * simulando una base de datos temporal.
 *
 * Users are stored in memory using an ArrayList
 * simulating a temporary database.
 */

@Tag(name = "Users", description = "User management endpoints")
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Servicio que contiene la lógica de negocio de usuarios.
     *
     * Service that contains the business logic for users.
     */
    private final UserService userService;

    /**
     * Constructor con inyección de dependencias.
     *
     * Constructor with dependency injection.
     *
     * @param userService servicio de usuarios
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET /users
     *
     * Obtiene la lista de usuarios almacenados en memoria.
     *
     * Returns the list of users stored in memory.
     *
     * Permite:
     *
     * - Ordenamiento dinámico mediante el parámetro sortedBy
     * - Filtrado dinámico mediante el parámetro filter
     *
     * Examples:
     *
     * /users?sortedBy=email
     * /users?filter=name+co+user
     */
    @Operation(
            summary = "Get all users",
            description = "Returns all users with optional sorting and filtering"
    )
    @GetMapping
    public List<User> getUsers(
            @RequestParam(required = false) String sortedBy,
            @RequestParam(required = false) String filter) {

        return userService.getUsers(sortedBy, filter);
    }

    /**
     * POST /users
     *
     * Crea un nuevo usuario en memoria.
     *
     * Creates a new user in memory.
     *
     * Validaciones:
     *
     * - RFC válido
     * - RFC único
     * - Teléfono válido
     *
     * La contraseña se encripta usando AES256.
     *
     * createdAt se genera automáticamente
     * usando la zona horaria de Madagascar.
     */
    @Operation(
            summary = "Create user",
            description = "Creates a new user with validations and AES encrypted password"
    )
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
     *
     * Actualiza parcialmente un usuario usando su ID.
     *
     * Permite modificar:
     *
     * - email
     * - name
     * - phone
     * - password
     * - taxId
     *
     * La contraseña se vuelve a encriptar automáticamente.
     */
    @Operation(
            summary = "Update user",
            description = "Partially updates a user by ID"
    )
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
     *
     * Elimina un usuario usando su ID.
     *
     * Si no existe devuelve 404.
     */
    @Operation(
            summary = "Delete user",
            description = "Deletes a user by ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {

        boolean deleted = userService.deleteUser(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

}