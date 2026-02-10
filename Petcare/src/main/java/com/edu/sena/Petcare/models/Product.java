package com.edu.sena.Petcare.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "product",
        uniqueConstraints = @UniqueConstraint(name = "uk_product", columnNames = {"name",  "brand"})
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String picture; 

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 100, nullable = false)
    private String brand;

    @Column(length = 500, nullable = false)
    private String description;

    //relacion OneToMany con BillDetail
    @OneToMany(mappedBy = "product")
    private List<BillDetail> billDetail;

    //relacion ManyToMany con Wishlists
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "wishlists_product",
                joinColumns = @JoinColumn(name="id_product"),
                inverseJoinColumns = @JoinColumn(name="id_wishlists")
    )
    private List<Wishlist> wishlists;

    //relacion ManyToMany con categories
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "product_category",
                joinColumns = @JoinColumn(name="id_product"),
                inverseJoinColumns = @JoinColumn(name="id_category")
    )
    private List<Category> categories;
}
