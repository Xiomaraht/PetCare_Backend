package com.edu.sena.Petcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.sena.Petcare.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByDocumentType_IdAndDocumentNumber(Long documentTypeId, String documentNumber);
}
