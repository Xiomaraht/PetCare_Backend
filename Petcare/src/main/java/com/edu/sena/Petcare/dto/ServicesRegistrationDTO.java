package com.edu.sena.Petcare.dto;

import lombok.*;
import java.util.List;

@Data
public class ServicesRegistrationDTO {

    private Boolean status;
    private String name;
    private String description;
    private String picture;

    private List<Long> veterinaryClinicIds;
}
