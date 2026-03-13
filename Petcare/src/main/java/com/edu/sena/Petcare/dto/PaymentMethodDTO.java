package com.edu.sena.Petcare.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class PaymentMethodDTO {

    private long id;
    private String tokenPaymentMethod;
    private String tokenClientGateway;
    private String brand;
    private Integer lastFourDigits;
    private String expirationDate;
    private Boolean isDefault;
    private Long customerId;
}
