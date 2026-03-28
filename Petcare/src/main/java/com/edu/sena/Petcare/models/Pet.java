package com.edu.sena.Petcare.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    // Relación blindada con @JsonIgnore para evitar LazyInitializationException
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_race")
    private Race raza; 

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer; 

    // Métodos explícitos para asegurar compatibilidad si Lombok falla por el JDK
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
