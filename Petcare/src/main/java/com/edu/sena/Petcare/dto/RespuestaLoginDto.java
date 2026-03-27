package com.edu.sena.Petcare.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaLoginDto {
    private String token;
    private String nombreCompleto;
    private String rol;
    private Long userId;
    private Long customerId;
    private Long clinicId;
}
