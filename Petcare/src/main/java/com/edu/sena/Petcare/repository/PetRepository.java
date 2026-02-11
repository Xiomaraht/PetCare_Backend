package com.edu.sena.Petcare.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.sena.Petcare.models.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    //Obtiene una lista de mascotas buscando el id del Customer
    List<Pet> findByCustomerId(Long customerId);
}
