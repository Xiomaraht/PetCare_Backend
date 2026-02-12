package com.edu.sena.Petcare.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    
    private Long id;
    private String name;
    private String picture;
    private BigDecimal price;
    private Integer stock;
    private String brand;
    private String description;
    private List<Long> categoryIds; 
}
