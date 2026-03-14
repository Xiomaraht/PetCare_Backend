package com.edu.sena.Petcare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edu.sena.Petcare.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByNameAndBrand(String name, String brand);

    //Estos metodos son para evitar el problema de N+1 al cargar las categorias junto con los productos
    @Query("SELECT p FROM Product p JOIN FETCH p.categories")
    List<Product> findAllWithCategories();

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.categories c WHERE p.id = :id")
    Optional<Product> findByIdWithCategories(@Param("id") Long id);

    List<Product> findByVeterinaryClinicId(Long clinicId);
}
