package com.edu.sena.Petcare.models;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
    private String address;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_document_type")
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "id_neighborhood")
    private Neighborhood barrioCliente;

    @OneToMany(mappedBy = "customer")
    private List<Pet> petCustomer;

    @OneToMany(mappedBy = "customer")
    private List<Bill> facturas;

    @OneToMany(mappedBy = "customer")
    private List<MethodPaymentCustomer> metodosPagoCliente;

    @OneToMany(mappedBy = "customer")
    private List<Wishlist> whishlists;
}
