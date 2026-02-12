package com.edu.sena.Petcare.service;

import java.util.List;

import com.edu.sena.Petcare.dto.WishlistDTO;

public interface WishlistService {

    WishlistDTO newWishlist(WishlistDTO wishlistDTO);

    List<WishlistDTO> getAllWishlists();

    WishlistDTO getSpecificWishlist(Long id);

    WishlistDTO updateWishlist(Long id, WishlistDTO wishlistDTO);

    void deleteWishlist(Long id);

}
