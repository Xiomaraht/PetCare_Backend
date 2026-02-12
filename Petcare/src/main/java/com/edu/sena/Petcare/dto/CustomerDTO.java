package com.edu.sena.Petcare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Este DTO funcionará como respuesta, y como los datos que podran ser visibles para el customer
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long id;

    private String address;

    private String addressDetail;

    private String phone;

    private UserGetDTO user;

    private String documenTypeName;

    private String neighborhoodName;
}