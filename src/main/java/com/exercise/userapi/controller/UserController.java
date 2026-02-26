package com.exercise.userapi.controller;

import com.exercise.userapi.model.User;
import com.exercise.userapi.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Controlador REST que expone los endpoints relacionados a usuarios.
 *
 * REST Controller that exposes user-related endpoints.
 *
 * Este controlador permite:
 * This controller allows:
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
     * @param userService user service
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
     * Allows:
     *
     * - Ordenamiento dinámico mediante el parámetro sortedBy
     * - Filtrado dinámico mediante el parámetro filter
     *
     * - Dynamic sorting using sortedBy parameter
     * - Dynamic filtering using filter parameter
     *
     * Ejemplos:
     * Examples:
     *
     * /users?sortedBy=email
     * /users?filter=name+co+user
     *
     * @param sortedBy campo por el cual ordenar
     * @param sortedBy field used for sorting
     *
     * @param filter filtro dinámico
     * @param filter dynamic filter
     *
     * @return lista de usuarios
     * @return list of users
     */
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
     * Validaciones aplicadas:
     * Applied validations:
     *
     * - taxId debe tener formato RFC
     * - taxId debe ser único
     * - phone debe tener 10 dígitos
     * - phone debe cumplir AndresFormat
     *
     * - taxId must have RFC format
     * - taxId must be unique
     * - phone must contain 10 digits
     * - phone must pass AndresFormat validation
     *
     * La contraseña se almacena usando cifrado AES256.
     *
     * Password is stored using AES256 encryption.
     *
     * El campo createdAt se genera automáticamente
     * usando la zona horaria de Madagascar.
     *
     * createdAt is automatically generated
     * using Madagascar timezone.
     *
     * @param user usuario a crear
     * @param user user to create
     *
     * @return usuario creado o error 400
     * @return created user or 400 error
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
     *
     * Actualiza parcialmente un usuario usando su ID.
     *
     * Partially updates a user using its ID.
     *
     * Permite modificar:
     * Allows updating:
     *
     * - email
     * - name
     * - phone
     * - password
     * - taxId
     *
     * La contraseña se vuelve a encriptar automáticamente.
     *
     * Password is automatically encrypted again.
     *
     * @param id identificador del usuario
     * @param id user identifier
     *
     * @param updates mapa con los campos a actualizar
     * @param updates map with fields to update
     *
     * @return usuario actualizado o 404 si no existe
     * @return updated user or 404 if not found
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
     *
     * Elimina un usuario usando su ID.
     *
     * Deletes a user using its ID.
     *
     * Si el usuario no existe devuelve 404.
     *
     * If the user does not exist returns 404.
     *
     * @param id identificador del usuario
     * @param id user identifier
     *
     * @return 200 si fue eliminado
     * @return 404 si no existe
     *
     * @return 200 if deleted
     * @return 404 if not found
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