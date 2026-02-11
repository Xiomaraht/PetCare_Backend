package com.edu.sena.Petcare.repository;

import com.edu.sena.Petcare.models.Exams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamsRepository extends JpaRepository<Exams, Long> {

    List<Exams> findByStatusTrue();
}
