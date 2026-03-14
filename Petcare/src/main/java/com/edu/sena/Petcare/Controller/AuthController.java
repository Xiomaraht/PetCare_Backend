package com.edu.sena.Petcare.controller;

import com.edu.sena.Petcare.dto.LoginDto;
import com.edu.sena.Petcare.dto.RespuestaLoginDto;
import com.edu.sena.Petcare.models.User;
import com.edu.sena.Petcare.repository.UserRepository;
import com.edu.sena.Petcare.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import com.edu.sena.Petcare.service.EmailService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    public AuthController(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        String token = jwtService.generateToken(user);

        RespuestaLoginDto respuesta = new RespuestaLoginDto();
        respuesta.setToken(token);
        respuesta.setNombreCompleto((user.getFirstName() != null ? user.getFirstName() : "") + " "
                + (user.getLastName() != null ? user.getLastName() : ""));
        respuesta.setRol(user.getAuthority() != null ? user.getAuthority().getName() : "USER");

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        if (email == null) return ResponseEntity.badRequest().body("Email requerido");
        
        userRepository.findByEmail(email).ifPresent(user -> {
            String token = UUID.randomUUID().toString();
            user.setResetToken(token);
            user.setResetTokenExpiry(LocalDateTime.now().plusHours(1));
            userRepository.save(user);
            emailService.sendPasswordResetEmail(user.getEmail(), token);
        });
        
        return ResponseEntity.ok("Si el correo existe, se enviará un enlace de recuperación.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");
        String newPassword = payload.get("newPassword");
        
        if (token == null || newPassword == null) {
            return ResponseEntity.badRequest().body("Token y nueva contraseña requeridos.");
        }
        
        if (!newPassword.matches("^(?=.*\\d).{8,}$")) {
            return ResponseEntity.badRequest().body("La contraseña debe tener mínimo 8 caracteres y al menos 1 número");
        }
        
        User user = userRepository.findByResetToken(token).orElse(null);
        if (user == null || user.getResetTokenExpiry() == null || user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido o expirado");
        }
        
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        userRepository.save(user);
        
        return ResponseEntity.ok("Contraseña actualizada con éxito");
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Sesión cerrada exitosamente");
    }
}
