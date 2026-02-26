package com.exercise.userapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicia la aplicación Spring Boot.
 *
 * Main class that starts the Spring Boot application.
 *
 * Esta clase es el punto de entrada del sistema REST API.
 *
 * This class is the entry point of the REST API system.
 *
 * Al ejecutarse:
 * When executed:
 *
 * - Inicializa Spring Boot
 * - Carga los controladores REST
 * - Carga los servicios
 * - Inicializa los usuarios en memoria
 *
 * - Initializes Spring Boot
 * - Loads REST controllers
 * - Loads services
 * - Initializes in-memory users
 *
 * La aplicación simula una base de datos en memoria
 * usando una lista de usuarios.
 *
 * The application simulates an in-memory database
 * using a list of users.
 *
 * URL base por defecto:
 * Default base URL:
 *
 * http://localhost:8080
 */
@SpringBootApplication
public class UserapiApplication {

    /**
     * Método principal que arranca la aplicación.
     *
     * Main method that starts the application.
     *
     * Ejecuta el servidor embebido de Spring Boot
     * y habilita los endpoints REST.
     *
     * Runs the embedded Spring Boot server
     * and enables REST endpoints.
     *
     * @param args argumentos de ejecución
     * @param args execution arguments
     */
    public static void main(String[] args) {

        SpringApplication.run(UserapiApplication.class, args);

    }
}