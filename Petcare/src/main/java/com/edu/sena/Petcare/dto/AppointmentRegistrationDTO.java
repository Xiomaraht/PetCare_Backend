package com.edu.sena.Petcare.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AppointmentRegistrationDTO {
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason;
    private Long customerId;
    private Long clinicId;
    private Long serviceId;
    private Long petId;
    private String status;
}
