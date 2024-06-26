package main.java.com.example.server.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtAuth {
    // Secret key should be at least 32 bytes (256 bits) for HS256
    private static final String SECRET_KEY_STRING = "a_long_and_secure_secret_key_for_jwt_generation_123456";
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    // Method to verify a JWT token and return its claims
    public static Claims verifyToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to generate a JWT token for a given userId
    public static String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}
