package com.edu.sena.Petcare.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pets")

public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String imageUrl;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(length = 20, unique = true)
    private String microchip;

    @Column(length = 15)
    private String color;

    @Column(length = 10)
    private String weight;

    @Column(nullable = false, length = 10)
    private String gender;
    
    @Column(nullable = false)
    private Boolean status = true;

    //relacion OneToMany con Race
    @ManyToOne
    @JoinColumn(name = "id_race")
    private Race raza; 

    //relacion OneToMany con Customer
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer; 
    
}
