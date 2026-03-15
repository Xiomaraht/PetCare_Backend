package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@lombok.RequiredArgsConstructor
public class JwtService {

    private final com.edu.sena.Petcare.repository.VeterinaryClinicRepository clinicRepository;

    private Key getSecretKey() {
        String secret = "petcare_secret_key_petcare_secret_key_petcare_123456789"; // Must be strong
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private final long EXPIRATION = 3600000; // 1 hour

    public String generateToken(User user) {
        String fullName = (user.getFirstName() != null ? user.getFirstName() : "") + " " +
                (user.getLastName() != null ? user.getLastName() : "");

        String role = user.getAuthority() != null ? user.getAuthority().getName() : "USER";

        io.jsonwebtoken.JwtBuilder builder = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("id", user.getId())
                .claim("nombreCompleto", fullName.trim())
                .claim("rol", role);

        if ("ROLE_VETERINARIAN".equals(role)) {
            clinicRepository.findByUser_Id(user.getId()).ifPresent(clinic -> {
                builder.claim("clinicId", clinic.getId());
            });
        }

        return builder
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
