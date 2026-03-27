package com.edu.sena.Petcare.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "medical_records")
public class MedicalRecord extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // VACUNA, CONSULTA, RECETA, HALLAZGO, ALERGIA, CONDICION

    @Column(nullable = false)
    private LocalDate recordDate;

    @Column(length = 1000)
    private String description;

    @Column(length = 2000)
    private String findings;

    @Column(length = 2000)
    private String treatment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinary_clinic_id")
    private VeterinaryClinic veterinaryClinic;
}
