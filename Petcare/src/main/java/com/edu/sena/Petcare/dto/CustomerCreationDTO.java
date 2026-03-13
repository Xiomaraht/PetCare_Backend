package com.edu.sena.Petcare.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCreationDTO {

    private String documentNumber;
    private String phone;
    private String address;
    private String addressDetail;
    private String birthdate;
    private Long userId;
    private Long documentTypeId;
    private Long neighborhoodId;
}
