package com.edu.sena.Petcare.controller;

import com.edu.sena.Petcare.dto.UserDTO;
import com.edu.sena.Petcare.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO created = userService.save(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "mensaje", "Usuario creado exitosamente",
                    "data", created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "No se pudo crear el usuario",
                    "detalle", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
}
