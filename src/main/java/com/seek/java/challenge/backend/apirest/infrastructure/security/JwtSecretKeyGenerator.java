package com.seek.java.challenge.backend.apirest.infrastructure.security;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretKeyGenerator {

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[64];
        secureRandom.nextBytes(key);

        String base64UrlEncodedKey = Base64.getUrlEncoder().withoutPadding().encodeToString(key);
        System.out.println("Base64 URL Encoded Secret Key: " + base64UrlEncodedKey);
    }
}
