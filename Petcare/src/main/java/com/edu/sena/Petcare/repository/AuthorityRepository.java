package com.edu.sena.Petcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.sena.Petcare.models.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Optional<Authority> findByName(String name);
}