package main.java.com.example.server.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtils {
    private static final Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    // Generate JWT token
    public static String generateJwtToken(int userId, String username, long expirationMillis) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }

    // Validate JWT token
    public static boolean validateJwtToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken);

            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // Extract user ID from JWT token
    public static int extractUserIdFromJwtToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken);

            return claimsJws.getBody().get("userId", Integer.class);
        } catch (Exception e) {
            return -1; // Invalid token or error
        }
    }
}

