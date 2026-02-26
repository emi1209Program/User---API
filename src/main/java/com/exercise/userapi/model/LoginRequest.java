package com.exercise.userapi.model;

/**
 * Clase que representa la solicitud de autenticación (login).
 *
 * Class that represents the authentication (login) request.
 *
 * Esta clase se utiliza en el endpoint:
 * This class is used in the endpoint:
 *
 * POST /login
 *
 * Contiene las credenciales necesarias para autenticar un usuario.
 *
 * Contains the credentials required to authenticate a user.
 *
 * El taxId funciona como nombre de usuario.
 * The taxId works as the username.
 *
 * La contraseña será encriptada usando AES256 antes de validarse.
 *
 * The password will be encrypted using AES256 before validation.
 *
 * Ejemplo JSON:
 * Example JSON:
 *
 * {
 *   "taxId": "AARR990101XXX",
 *   "password": "password1"
 * }
 */
public class LoginRequest {

    /**
     * Identificador fiscal del usuario (RFC).
     *
     * User tax identifier (RFC).
     *
     * Este campo funciona como nombre de usuario
     * durante el proceso de autenticación.
     *
     * This field works as the username
     * during the authentication process.
     */
    private String taxId;

    /**
     * Contraseña del usuario en texto plano.
     *
     * User password in plain text.
     *
     * Esta contraseña será encriptada mediante AES256
     * antes de compararse con la almacenada.
     *
     * This password will be encrypted using AES256
     * before being compared with the stored one.
     */
    private String password;

    /**
     * Constructor vacío requerido por Spring/Jackson
     * para convertir automáticamente JSON a objeto Java.
     *
     * Empty constructor required by Spring/Jackson
     * to automatically convert JSON into a Java object.
     */
    public LoginRequest() {}

    /**
     * Obtiene el taxId del usuario.
     *
     * Returns the user taxId.
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * Asigna el taxId del usuario.
     *
     * Sets the user taxId.
     */
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * Returns the user password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Asigna la contraseña del usuario.
     *
     * Sets the user password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}