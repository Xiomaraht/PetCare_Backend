package com.edu.sena.Petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.sena.Petcare.models.Race;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long>{
    List<Race> findByEspecie_Id(Long specieId); 
}
