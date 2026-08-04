package org.example.gymtrackerspring.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.gymtrackerspring.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import io.jsonwebtoken.Jwts.*;

@Service
public class JwtService {
    private static final String SECRET = "LkdowqfknfvowjfwpnfLNFNJWidewnjow@#)!(#93-12903";
    private static final Duration EXPIRATION = Duration.ofHours(1);

    public String generateToken(User user){
        Instant now = Instant.now();
        return Jwts.builder().subject(user.getUsername())
                .claim("role", user.getRole())
                .expiration(Date.from(now.plus(EXPIRATION)))
                .issuedAt(Date.from(now)).signWith(getSigningKey()).compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = extractAllClaims(token);

        return claims.getSubject().equals(userDetails.getUsername())
                && !isTokenExpired(claims);
    }
}