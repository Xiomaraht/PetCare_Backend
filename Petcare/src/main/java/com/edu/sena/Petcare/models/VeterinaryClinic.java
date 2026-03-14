package com.edu.sena.Petcare.models;    

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "veterinary_clinic",
        uniqueConstraints = @UniqueConstraint(name = "uk_veterinary",  columnNames = {"id_document_type", "document_number"})
)
public class VeterinaryClinic {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 255)
    private String email;   

    @Column(name = "document_number", nullable = false, length = 100)
    private String documentNumber;


    //relacion OneToMany con VeterinaryClinic
    @ManyToOne
    @JoinColumn(name = "id_document_type")
    private DocumentType documentTypeVeterinary;

    //relacion ManyToMany con Services
    @ManyToMany(mappedBy = "veterinaryClinics")
    private List<Services> services;

    //relacion OneToOne con User
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    //relacion OneToMany con Bill
    @OneToMany(mappedBy = "veterinaryClinic")
    private List<Bill> facturas;

    //relacion OneToMany con Product
    @OneToMany(mappedBy = "veterinaryClinic")
    private List<Product> products;
}
