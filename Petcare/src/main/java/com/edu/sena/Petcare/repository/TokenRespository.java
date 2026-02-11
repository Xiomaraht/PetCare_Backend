package com.edu.sena.Petcare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edu.sena.Petcare.models.Token;

@Repository
public interface TokenRespository extends JpaRepository<Token, Long>{

    List<Token> findAllValidIsFalseOrRevokedIsFalseByUserId(Long userId);
    Optional<Token> findByToken(String token);
}
