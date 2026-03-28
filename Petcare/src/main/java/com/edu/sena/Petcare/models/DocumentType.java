package com.edu.sena.Petcare.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "document_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DocumentType {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 7)
    private String abreviation;

    //relacion ManyToOne con Customer
    @JsonIgnore
    @OneToMany(mappedBy = "documentType")
    private List<Customer> customers;

    //relacion ManyToOne con Employee
    @JsonIgnore
    @OneToMany(mappedBy = "documentType")
    private List<Employee> empleados;

}
