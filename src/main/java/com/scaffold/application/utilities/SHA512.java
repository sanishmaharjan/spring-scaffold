package com.scaffold.application.utilities;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class SHA512 {

    public static String generateSalt() {
        final Random r = new SecureRandom();
        byte[] salt = new byte[32];
        r.nextBytes(salt);
        return new String(Base64.getEncoder().encode(salt));
    }

    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));

            }

            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    public static Boolean comparePassword(String planPassword, String hashPassword, String salt) {
        String processedPassword = SHA512.hashPassword(planPassword, salt);
        return (hashPassword.equals(processedPassword));
    }
}
