package com.edu.sena.Petcare.dto;

import lombok.Data;

/*
    Este DTO es el que se expondra al 
    momento de actualizar campos en un usuario
*/

@Data
public class UserUpdateDTO {

    private String names;
    
    private String lastNames;
    
    private String email;
    
    private String imageUrl;
    
    private Long rolId; // El ID del rol que asignaré
}
