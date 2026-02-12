package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.CategoryDTO;
import com.edu.sena.Petcare.models.Category;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final ModelMapper modelMapper;

    // Conversion de DTO a Entity, llega del FRONT
    public Category toEntity(CategoryDTO CategoryDTO){
        return modelMapper.map(CategoryDTO, Category.class);
    }

    
    public void toEntity(CategoryDTO categoryDTO, Category customerExistente){
        modelMapper.map(categoryDTO, customerExistente);
    }

    public CategoryDTO toDTO(Category category){
        return modelMapper.map(category, CategoryDTO.class);
    }
}
