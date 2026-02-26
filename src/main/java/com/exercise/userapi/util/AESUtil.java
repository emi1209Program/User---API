package com.exercise.userapi.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

/**
 * Clase utilitaria para el cifrado de contraseñas usando AES256.
 *
 * Utility class for password encryption using AES256.
 *
 * Esta clase se utiliza para almacenar las contraseñas
 * de forma segura dentro de memoria.
 *
 * This class is used to store passwords securely in memory.
 *
 * El algoritmo utilizado es:
 * The algorithm used is:
 *
 * AES/ECB/PKCS5Padding
 *
 * La salida del cifrado se convierte a Base64 para poder
 * almacenarse como texto.
 *
 * The encrypted output is converted to Base64 so it can be
 * stored as text.
 *
 * Esta clase es utilizada por:
 * This class is used by:
 *
 * - UserService (createUser)
 * - UserService (login)
 * - UserService (updateUser)
 */
public class AESUtil {

    /**
     * Clave secreta utilizada para el cifrado AES256.
     *
     * Secret key used for AES256 encryption.
     *
     * Debe tener exactamente 32 caracteres para AES256.
     *
     * Must have exactly 32 characters for AES256.
     */
    private static final String SECRET_KEY = "12345678901234567890123456789012";

    /**
     * Encripta una contraseña usando AES256.
     *
     * Encrypts a password using AES256.
     *
     * Pasos:
     * Steps:
     *
     * 1. Convierte la clave secreta en bytes
     * 2. Crea la clave AES
     * 3. Inicializa el cifrado
     * 4. Encripta la contraseña
     * 5. Convierte el resultado a Base64
     *
     * 1. Converts secret key into bytes
     * 2. Creates AES key
     * 3. Initializes cipher
     * 4. Encrypts password
     * 5. Converts result to Base64
     *
     * @param password contraseña en texto plano
     * @param password plain text password
     *
     * @return contraseña encriptada en Base64
     * @return encrypted password in Base64
     */
    public static String encrypt(String password) {
        try {

            // Convierte la clave secreta en bytes
            // Converts secret key into bytes
            byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);

            // Crea la clave AES
            // Creates AES key
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

            // Configuración del algoritmo AES
            // AES algorithm configuration
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // Inicializa el cifrado en modo ENCRYPT
            // Initializes cipher in ENCRYPT mode
            cipher.init(Cipher.ENCRYPT_MODE, key);

            // Encripta la contraseña
            // Encrypts password
            byte[] encrypted = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));

            // Convierte a Base64 para almacenamiento como texto
            // Converts to Base64 for text storage
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {

            // Imprime error en consola
            // Prints error in console
            e.printStackTrace();

            // Lanza excepción si falla el cifrado
            // Throws exception if encryption fails
            throw new RuntimeException("Error encrypting password");
        }
    }
}