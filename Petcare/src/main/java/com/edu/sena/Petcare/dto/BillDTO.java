package com.edu.sena.Petcare.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillDTO {
    private Long id;
    private LocalDateTime date;
    private Double total;
    private Long customerId;
    private String customerName;
    private String paymentStatus; // PAID, PENDING, etc.
}
