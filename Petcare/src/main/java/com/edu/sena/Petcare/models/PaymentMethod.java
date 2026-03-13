package com.edu.sena.Petcare.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "method_payment_customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentMethod {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token_payment_method", nullable = false, unique = true)
    private String tokenPaymentMethod;

    @Column(name = "token_client_gateway", nullable = false)
    private String tokenClientGateway;

    @Column(nullable = false)
    private String brand;

    @Column(name = "last_four_digits", nullable = false)
    private Integer lastFourDigits;

    @Column(name = "expiration_date", length = 7, nullable = false)
    private String expirationDate;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @OneToMany(mappedBy = "metodoCliente")
    private List<Bill> bill;

    @OneToMany(mappedBy = "methodTransaction")
    private List<Transactions> transactions;
}
