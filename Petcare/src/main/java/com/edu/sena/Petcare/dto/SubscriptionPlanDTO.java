package com.edu.sena.Petcare.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlanDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer durationDays;
    private String description;
    private Boolean active;
}
