package com.edu.sena.Petcare.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.edu.sena.Petcare.dto.ProductDTO;
import com.edu.sena.Petcare.dto.ProductRegistrationDTO; 
import com.edu.sena.Petcare.exception.ResourceNotFoundException;
import com.edu.sena.Petcare.mapper.ProductMapper;
import com.edu.sena.Petcare.models.Category;
import com.edu.sena.Petcare.models.Product;
import com.edu.sena.Petcare.repository.CategoryRepository;
import com.edu.sena.Petcare.repository.ProductRepository;
import com.edu.sena.Petcare.service.ProductService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    // La firma del método debe coincidir con la Interfaz y el DTO
    public ProductDTO create(ProductRegistrationDTO dto) { 

        Product product = ProductMapper.toEntity(dto);
        
        List<Category> categories = (List<Category>) categoryRepository.findAllById(dto.getCategoryIds());

        if (categories.isEmpty() && dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()) {
             throw new ResourceNotFoundException("One or more Category IDs provided were not found.");
        }
        
        product.setCategories(categories);

        return ProductMapper.toDTO(productRepository.save(product));
    }

    @Override
    // La firma del método debe coincidir con la Interfaz y el DTO
    public ProductDTO update(Long id, ProductRegistrationDTO dto) { 

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        // Actualizar campos
        product.setName(dto.getName());
        product.setPicture(dto.getPicture());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setBrand(dto.getBrand());
        product.setDescription(dto.getDescription());
        
        // Obtener y validar categorías
        List<Category> categories = (List<Category>) categoryRepository.findAllById(dto.getCategoryIds());

        if (categories.isEmpty() && dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()) {
             throw new ResourceNotFoundException("One or more Category IDs provided were not found for update.");
        }

        product.setCategories(categories);

        return ProductMapper.toDTO(productRepository.save(product));
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAllWithCategories()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id)); 
        return ProductMapper.toDTO(product);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID: " + id + " for deletion"); 
        }
        productRepository.deleteById(id);
    }
}
