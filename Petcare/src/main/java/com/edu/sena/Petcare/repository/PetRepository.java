package com.edu.sena.Petcare.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.sena.Petcare.models.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    //Obtiene una lista de mascotas buscando el id del Customer
    List<Pet> findByCustomerId(Long customerId);

    @org.springframework.data.jpa.repository.Query("SELECT DISTINCT p FROM Pet p JOIN Appointment a ON a.customer.id = p.customer.id WHERE a.veterinaryClinic.id = :clinicId")
    java.util.List<Pet> findPetsByClinicId(@org.springframework.data.repository.query.Param("clinicId") Long clinicId);
}
