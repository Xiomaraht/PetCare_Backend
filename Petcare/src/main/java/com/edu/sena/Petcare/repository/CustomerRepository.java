package com.edu.sena.Petcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.sena.Petcare.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    boolean existsByDocumentNumberAndDocumentTypeId(String documentNumber, Long documentType);
    Optional<Customer> findByUser_Id(Long userId);
}
