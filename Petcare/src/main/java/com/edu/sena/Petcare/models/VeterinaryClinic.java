package com.edu.sena.Petcare.models;    

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VeterinaryClinic {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 50)
    private String nit;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 255)
    private String email;   

    @Column(name = "document_number", nullable = false, length = 100)
    private String documentNumber;

    @Column(length = 1000)
    private String description;

    @Column(length = 255)
    private String openingHours;

    @Column(nullable = false, length = 20)
    private String status = "APPROVED"; // Default to APPROVED for now as requested

    @Column(columnDefinition = "TEXT")
    private String picture;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_document_type")
    private DocumentType documentTypeVeterinary;

    @JsonIgnore
    @ManyToMany(mappedBy = "veterinaryClinics")
    private List<Services> services;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    //relacion OneToMany con Bill
    @JsonIgnore
    @OneToMany(mappedBy = "veterinaryClinic")
    private List<Bill> facturas;

    //relacion OneToMany con Product
    @JsonIgnore
    @OneToMany(mappedBy = "veterinaryClinic")
    private List<Product> products;
}
