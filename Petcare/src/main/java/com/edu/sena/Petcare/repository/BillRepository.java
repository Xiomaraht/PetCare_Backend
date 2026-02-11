package com.edu.sena.Petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.sena.Petcare.models.Bill;

@Repository
public interface BillRepository extends JpaRepository <Bill, Long>{

}
