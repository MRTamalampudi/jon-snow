package com.expenses.jonsnow.service;

import com.expenses.jonsnow.model.SecurityUser;
import com.expenses.jonsnow.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class JWTService {

    @Value("${session.cookie.name}")
    @Getter
    private String cookieName;

    @Value("${session.jwt.secret}")
    @Getter
    private String jwtSecret;

    public String generateToken(User user){
        return Jwts.builder()
                .claim("email",user.getEmail())
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public Jws<Claims> parseToken(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                .build().parseSignedClaims(token);
    }
}
