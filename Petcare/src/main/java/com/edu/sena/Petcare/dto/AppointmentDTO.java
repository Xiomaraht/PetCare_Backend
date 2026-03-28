package com.edu.sena.Petcare.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDTO {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime appointmentTime;

    private String reason;
    private String status;
    private Long customerId;
    private String customerName;
    private Long veterinaryClinicId;
    private String veterinaryClinicName;
    private Long petId;
    private String petName;
    private Long serviceId;
    private String serviceName;
}
