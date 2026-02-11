package com.edu.sena.Petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.sena.Petcare.models.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long>{

}
