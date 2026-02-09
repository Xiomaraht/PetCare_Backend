package com.edu.sena.Petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "employee",
        uniqueConstraints = @UniqueConstraint(name = "uk_employee", columnNames = {"id_document_type", "document_number"})
)
public class Employee {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_number", nullable = false, length = 15)
    private String documentNumber;

    @Column(length = 255)
    private String address;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(length = 20)
    private String bankAccount;

    //relacion OneToMany con User
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    //relacion OneToMany con DocumentType
    @ManyToOne
    @JoinColumn(name = "id_document_type")
    private DocumentType documentType;

    //relacion OneToMany con Bill
    @OneToMany(mappedBy = "empleado")
    private List<Bill> facturas;

}
