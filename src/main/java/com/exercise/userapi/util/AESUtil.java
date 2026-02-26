package com.exercise.userapi.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class AESUtil {

    private static final String SECRET_KEY = "12345678901234567890123456789012";

    public static String encrypt(String password) {
        try {

            byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);

            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encrypted = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error encrypting password");
        }
    }
}