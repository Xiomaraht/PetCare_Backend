package com.edu.sena.Petcare.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.edu.sena.Petcare.dto.CategoryDTO;
import com.edu.sena.Petcare.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> obtenerCategorias() {
        List<CategoryDTO> categorias = categoryService.obtenerCategories();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
}
