package com.edu.sena.Petcare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetDTO {

    private Long id;

    private String names;

    private String lastNames;

    private String email;

    private String imageUrl;

}
