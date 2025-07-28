package com.academia.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.KeysetScrollPosition;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final long JWT_TOKEN_EXPIRATION = 5 * 60 * 60 * 1000;

    @Value("${jjwt.secret}")
    private String secret_key_string;

    public String generateToken(UsuarioSecurity usuarioSecurity) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", usuarioSecurity.getRoles());
        claims.put("username", usuarioSecurity.getUsername());

        SecretKey secretKey = Keys.hmacShaKeyFor(secret_key_string.getBytes());
        return Jwts.builder()
                .signWith(secretKey)
                .subject(usuarioSecurity.getUsername())
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION))
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret_key_string.getBytes());
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token){
        return getAllClaimsFromToken(token).getExpiration();
    }

    public boolean isValidToken(String token) {
        return !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
