package com.edu.sena.Petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "neighborhood")
public class Neighborhood {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    //relacion OneToMany con Locality
    @ManyToOne
    @JoinColumn(name = "id_locality")
    private Locality locality;

    //relacion OneToMany con Customer
    @OneToMany(mappedBy = "barrioCliente")
    private List<Customer> clientes;
}