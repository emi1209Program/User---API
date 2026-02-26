package com.exercise.userapi.controller;

import com.exercise.userapi.model.LoginRequest;
import com.exercise.userapi.model.User;
import com.exercise.userapi.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controlador REST encargado del proceso de autenticación de usuarios.
 *
 * REST Controller responsible for user authentication.
 *
 * Este controlador expone el endpoint:
 * POST /login
 *
 * This controller exposes the endpoint:
 * POST /login
 *
 * El taxId funciona como nombre de usuario.
 * The taxId works as the username.
 *
 * La contraseña es validada usando cifrado AES256.
 * The password is validated using AES256 encryption.
 */
@RestController
public class LoginController {

    /**
     * Servicio que contiene la lógica de negocio para los usuarios.
     * Service that contains the business logic for users.
     */
    private final UserService userService;

    /**
     * Constructor con inyección de dependencias.
     *
     * Constructor with dependency injection.
     *
     * @param userService servicio de usuarios
     * @param userService user service
     */
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint de autenticación de usuarios.
     *
     * User authentication endpoint.
     *
     * URL:
     * POST /login
     *
     * Recibe:
     * Receives:
     * - taxId (username)
     * - password
     *
     * Busca un usuario cuyo taxId coincida y cuya contraseña
     * coincida después de ser encriptada.
     *
     * Searches for a user whose taxId matches and whose password
     * matches after encryption.
     *
     * Respuestas:
     * Responses:
     *
     * 200 OK:
     * Usuario autenticado correctamente.
     * User authenticated successfully.
     *
     * 401 Unauthorized:
     * Credenciales incorrectas.
     * Invalid credentials.
     *
     * @param request contiene taxId y password
     * @param request contains taxId and password
     *
     * @return usuario autenticado o error 401
     * @return authenticated user or 401 error
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {

        // Llama al servicio para validar credenciales
        // Calls the service to validate credentials
        User user = userService.login(
                request.getTaxId(),
                request.getPassword()
        );

        // Si el usuario no existe o las credenciales son incorrectas
        // If user does not exist or credentials are incorrect
        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        // Si el login es exitoso devuelve el usuario
        // If login is successful returns the user
        return ResponseEntity.ok(user);
    }
}