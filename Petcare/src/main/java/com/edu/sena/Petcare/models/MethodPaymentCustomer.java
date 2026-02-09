package com.edu.sena.Petcare.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "method_payment_customer")
public class MethodPaymentCustomer {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token_payment_method", length = 255, unique = true, nullable = false)
    private String tokenPaymentMethod;

    @Column(name = "token_client_gateway", length = 255, nullable = false)
    private String tokenClientGateway;

    @Column(length = 50, nullable = false)
    private String brand;

    @Column(name = "last_four_digits", length = 50, nullable = false)
    private Integer lastFourDigits;

    @Column(name = "expiration_date",length = 7, nullable = false)
    private String expirationDate;

    @Column(name="is_default", nullable = false)
    private Boolean isDefault;

    @Column(name="creation_date", nullable = false)
    private LocalDateTime creationDate;

    //relacion OneToMany con Customer
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    //relacion OneToMany con Bill
    @OneToMany(mappedBy = "metodoCliente")
    private List<Bill> bill;

    //relacion OneToMany con Transactions
    @OneToMany(mappedBy = "methodTransaction")
    private List<Transactions> transactions;
}
