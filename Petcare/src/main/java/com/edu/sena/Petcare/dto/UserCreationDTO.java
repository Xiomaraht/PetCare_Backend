package com.edu.sena.Petcare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * ESTA ENTIDAD SE ENCARGA DE EXPONER LOS DATOS NECESARIOS UNICAMENTE PARA LA CREACION DEL
 * USUARIO, PARA NADA MAS SE UTILIZA ESTE DTO
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDTO {
    
    private String login;

    private String password;

    private String names;

    private String lastNames;

    private String email;

    private String imageUrl;

    @Builder.Default
    private Long rolId = 3L;
}
