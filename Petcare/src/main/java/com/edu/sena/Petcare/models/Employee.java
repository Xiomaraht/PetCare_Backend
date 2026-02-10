package com.edu.sena.Petcare.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(
    name = "employee",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_employee",
        columnNames = {"id_document_type", "document_number"}
    )
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_number", nullable = false, length = 15)
    private String documentNumber;

    private String address;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(length = 20)
    private String bankAccount;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_document_type")
    private DocumentType documentType;

    @OneToMany(mappedBy = "empleado")
    private List<Bill> facturas;
}
