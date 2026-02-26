package com.exercise.userapi;

import com.exercise.userapi.model.User;
import com.exercise.userapi.service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UserService.
 *
 * Pruebas unitarias para UserService.
 */
public class UserServiceTest {

    /**
     * Test para verificar que login funciona correctamente.
     *
     * Test to verify login works correctly.
     */
    @Test
    void loginSuccessTest() {

        UserService service = new UserService();

        // Crear usuario de prueba
        User newUser = new User();
        newUser.setEmail("test@mail.com");
        newUser.setName("Test");
        newUser.setPhone("+525512345678");
        newUser.setPassword("password1");
        newUser.setTaxId("AARR990101XXX");

        service.createUser(newUser);

        User user = service.login("AARR990101XXX", "password1");

        assertNotNull(user);

    }

    /**
     * 
     * Test para verificar login incorrecto.
     *
     * Test to verify invalid login.
     */
    @Test
    void loginFailTest() {

        UserService service = new UserService();

        User user = service.login("AARR990101XXX", "WRONG");

        assertNull(user);

    }

}