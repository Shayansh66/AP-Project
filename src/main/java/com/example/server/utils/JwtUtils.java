package main.java.com.example.server.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JWTUtils {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createJWT(Map<String, Object> claims, long expirationMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + expirationMillis))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static Claims decodeJWT(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}
