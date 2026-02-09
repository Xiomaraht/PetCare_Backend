package com.edu.sena.Petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "services")
public class Services {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length =255)
    private String description;

    @Column(columnDefinition = "TEXT")
    private String picture;

    //relacion de ManyToMany con VeterinaryClinic
    @ManyToMany 
    @JoinTable( name = "service_clinic",
                joinColumns = @JoinColumn(name = "id_service"),
                inverseJoinColumns = @JoinColumn(name = "id_veterinary_clinic")
    )
    private List<VeterinaryClinic> veterinaryClinics;
}
