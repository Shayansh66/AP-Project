package main.java.com.example.server.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JWTUtils {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createJWT(Map<String, Object> claims, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + ttlMillis))
                .signWith(key)
                .compact();
    }

    public static Map<String, Object> verifyJWT(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}



