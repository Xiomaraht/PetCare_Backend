package com.edu.sena.Petcare.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bill_taxes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillTaxes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_bill")
    private Bill bill;
}
