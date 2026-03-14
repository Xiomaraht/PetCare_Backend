package com.edu.sena.Petcare.mapper;

import com.edu.sena.Petcare.dto.ProductDTO;
import com.edu.sena.Petcare.dto.ProductRegistrationDTO;
import com.edu.sena.Petcare.models.Category; 
import com.edu.sena.Petcare.models.Product;
import java.util.stream.Collectors; 
import java.util.List; 

public class ProductMapper {

    public static Product toEntity(ProductRegistrationDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPicture(dto.getPicture());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setBrand(dto.getBrand());
        product.setDescription(dto.getDescription());
        
        return product;
    }

    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPicture(product.getPicture());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setBrand(product.getBrand());
        dto.setDescription(product.getDescription());


        if (product.getCategories() != null) {
            List<Long> categoryIds = product.getCategories().stream()
                    .map(Category::getId)
                    .collect(Collectors.toList());
            dto.setCategoryIds(categoryIds);
        }
        if (product.getVeterinaryClinic() != null) {
            dto.setClinicId(product.getVeterinaryClinic().getId());
            // Assuming VeterinaryClinic has an 'address' or 'name' we can use. 
            // Let's check the VeterinaryClinic model. It doesn't have a 'name' field, maybe it uses 'address' or we should add 'name'.
            // Actually, looking at Step 431, it has address, phone, email, documentNumber. 
            // Wait, usually clinics have a name. I'll check the User associated with it if it has a name.
            if (product.getVeterinaryClinic().getUser() != null) {
                dto.setClinicName(product.getVeterinaryClinic().getUser().getFirstName());
            }
        }
        
        return dto;
    }
}
