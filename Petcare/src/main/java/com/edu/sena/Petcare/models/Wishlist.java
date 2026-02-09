package com.edu.sena.Petcare.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wishlist")
public class Wishlist {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    //relacion OneToMany con customer
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    //relacion ManyToMany con Product
    @ManyToMany(mappedBy = "wishlists")
    private List<Product> products;
}