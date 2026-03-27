package com.edu.sena.Petcare.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.sena.Petcare.models.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    //Obtiene una lista de mascotas buscando el id del Customer
    List<Pet> findByCustomerId(Long customerId);

    java.util.Optional<Pet> findByMicrochip(String microchip);

    boolean existsByMicrochip(String microchip);

    @org.springframework.data.jpa.repository.Query("SELECT DISTINCT a.pet FROM Appointment a WHERE a.veterinaryClinic.id = :clinicId")
    java.util.List<Pet> findPetsByClinicId(@org.springframework.data.repository.query.Param("clinicId") Long clinicId);

    void deleteByMicrochip(String microchip);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.data.jpa.repository.Query("DELETE FROM Pet p WHERE p.microchip = '' OR p.microchip IS NULL")
    @jakarta.transaction.Transactional
    void deletePetsWithNoMicrochip();
}
