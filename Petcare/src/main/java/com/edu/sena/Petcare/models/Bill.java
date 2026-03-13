package com.edu.sena.Petcare.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime billDate;

    @Column(nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_veterinary_clinic")
    private VeterinaryClinic veterinaryClinic;

    // relación con Customer
    @ManyToOne
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;

    // relación con Employee
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee empleado;

    // relación con MethodPaymentCustomer
    @ManyToOne
    @JoinColumn(name = "id_method_payment_customer")
    private PaymentMethod metodoCliente;

    // relación con BillDetail
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillDetail> detalles;

    // relación con BillTaxes
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillTaxes> impuestos;
}
