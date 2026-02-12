package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.dto.ProductDTO;
import com.edu.sena.Petcare.dto.ProductRegistrationDTO;
import java.util.List;

public interface ProductService {
    
    ProductDTO create(ProductRegistrationDTO dto); 
    

    ProductDTO update(Long id, ProductRegistrationDTO dto);

    List<ProductDTO> findAll();

    ProductDTO findById(Long id);

    void delete(Long id);
}
