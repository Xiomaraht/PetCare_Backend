package com.edu.sena.Petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.sena.Petcare.models.VeterinaryClinic;

import java.util.Optional;

@Repository
public interface VeterinaryClinicRepository extends JpaRepository<VeterinaryClinic, Long>{
    Optional<VeterinaryClinic> findByUser_Id(Long userId);
    java.util.List<VeterinaryClinic> findByStatus(String status);
}
