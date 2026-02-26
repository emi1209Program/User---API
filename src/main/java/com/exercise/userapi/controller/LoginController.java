package com.exercise.userapi.controller;

import com.exercise.userapi.model.LoginRequest;
import com.exercise.userapi.model.User;
import com.exercise.userapi.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST encargado del proceso de autenticación de usuarios.
 *
 * REST Controller responsible for user authentication.
 *
 * Este controlador expone el endpoint:
 *
 * POST /login
 *
 * This controller exposes the endpoint:
 *
 * POST /login
 *
 * El taxId funciona como nombre de usuario.
 * The taxId works as the username.
 *
 * La contraseña es validada usando cifrado AES256.
 * The password is validated using AES256 encryption.
 */

@Tag(name = "Login", description = "User authentication endpoints")
@RestController
public class LoginController {

    /**
     * Servicio que contiene la lógica de negocio para los usuarios.
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
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST /login
     *
     * Endpoint de autenticación.
     *
     * Authentication endpoint.
     *
     * Recibe:
     *
     * - taxId
     * - password
     *
     * Returns:
     *
     * 200 → Usuario autenticado
     * 401 → Credenciales incorrectas
     *
     * The system searches a user by taxId
     * and validates the encrypted password.
     *
     * @param request login request
     * @return authenticated user or 401 error
     */
    @Operation(
            summary = "User login",
            description = "Authenticates a user using taxId and password with AES encryption"
    )
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {

        // Llama al servicio para validar credenciales
        // Calls the service to validate credentials
        User user = userService.login(
                request.getTaxId(),
                request.getPassword()
        );

        // Si las credenciales son incorrectas
        // If credentials are invalid
        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        // Login exitoso
        // Successful login
        return ResponseEntity.ok(user);
    }

}