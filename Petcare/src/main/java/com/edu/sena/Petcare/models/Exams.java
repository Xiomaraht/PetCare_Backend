package com.edu.sena.Petcare.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "exams")
@Data 
@NoArgsConstructor
@AllArgsConstructor 
public class Exams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    
    @Column(nullable = false)
    private Boolean status = true; 

}
