package com.edu.sena.Petcare.service.impl;

import java.util.List;



import org.springframework.stereotype.Service;

import com.edu.sena.Petcare.dto.CategoryDTO;
import com.edu.sena.Petcare.mapper.CategoryMapper;
import com.edu.sena.Petcare.models.Category;
import com.edu.sena.Petcare.repository.CategoryRepository;
import com.edu.sena.Petcare.service.CategoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    
    @Override
    public List<CategoryDTO> obtenerCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toDTO).toList();
    }



}
