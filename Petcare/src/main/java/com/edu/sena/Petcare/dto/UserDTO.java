package com.edu.sena.Petcare.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    private String login;

    private String names;

    private String lastNames;

    private String email;

    private String imageUrl;

    private Long rolId;

}
