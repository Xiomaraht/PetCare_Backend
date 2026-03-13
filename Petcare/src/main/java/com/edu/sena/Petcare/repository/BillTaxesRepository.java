package com.edu.sena.Petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.sena.Petcare.models.BillTaxes;

@Repository
public interface BillTaxesRepository extends JpaRepository<BillTaxes, Long>{

}
