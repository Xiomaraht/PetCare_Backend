package com.edu.sena.Petcare.dto;

import com.edu.sena.Petcare.models.SubscriptionStatus;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicSubscriptionDTO {
    private Long id;
    private Long clinicId;
    private String clinicName;
    private Long planId;
    private String planName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private SubscriptionStatus status;
    private String paymentReference;
    private Boolean autoRenew;
}
