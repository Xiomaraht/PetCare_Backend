package com.edu.sena.Petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "race")
public class Race {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    //relacion OneToMany con Specie
    @ManyToOne
    @JoinColumn(name = "id_specie")
    private Specie especie;

    //relacion OneToMany con Pet
    @OneToMany(mappedBy = "raza")
    private List<Pet> mascotas;
}
