package com.edu.sena.Petcare.Controller;

import com.edu.sena.Petcare.Controller.Request.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        // Por ahora solo valida
        return ResponseEntity.ok("Usuario válido");
    }
}
