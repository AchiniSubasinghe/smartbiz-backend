package com.smartbiz.util;

import com.smartbiz.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    private final long expirationMs;
    private final UserService userService;


    public JwtUtil(
            UserService userService,
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration-ms}") long expirationMs
    ) {
        this.userService = userService;
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.expirationMs = expirationMs;
    }

    // ✅ Generate JWT Token
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extract Username from Token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ✅ Validate Token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // ✅ Check if Token is Expired
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // ✅ Extract All Claims (Payload)
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("Invalid or expired JWT token", e);
        }
    }

}
