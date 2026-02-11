package com.edu.sena.Petcare.Controller.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    @Size(min = 4, max = 50)
    private String username;

    @NotBlank
    @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres")
    @Pattern(
        regexp = ".*\\d.*",
        message = "La contraseña debe contener al menos un número"
    )
    private String password;

    @NotBlank
    private String names;

    @NotBlank
    private String lastNames;

    @Email
    private String email;
}