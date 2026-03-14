package com.edu.sena.Petcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.sena.Petcare.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUser_Id(Long userId);

    @org.springframework.data.jpa.repository.Query("SELECT DISTINCT c FROM Customer c JOIN Appointment a ON a.customer.id = c.id WHERE a.veterinaryClinic.id = :clinicId")
    java.util.List<Customer> findCustomersByClinicId(@org.springframework.data.repository.query.Param("clinicId") Long clinicId);
}
