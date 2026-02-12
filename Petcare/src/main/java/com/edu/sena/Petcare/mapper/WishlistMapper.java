package com.edu.sena.Petcare.mapper;

import java.util.stream.Collectors; 

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.WishlistDTO;
import com.edu.sena.Petcare.models.Wishlist;
import com.edu.sena.Petcare.models.Product; 
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WishlistMapper {

    private final ModelMapper modelMapper;

    public Wishlist toEntity(WishlistDTO wishlistDTO){
        return modelMapper.map(wishlistDTO, Wishlist.class);
    }

    public void toEntity(WishlistDTO wishlistDTO, Wishlist wishlistExistente){
        modelMapper.map(wishlistDTO, wishlistExistente);
    }

    public WishlistDTO toDTO(Wishlist wishlist){
        if (wishlist == null) {
            return null;
        }

        WishlistDTO wishlistDTO = new WishlistDTO();


        wishlistDTO.setId(wishlist.getId());
        wishlistDTO.setCreateDate(wishlist.getCreateDate().toString());

        if (wishlist.getCustomer() != null) {
            wishlistDTO.setCustomerId(wishlist.getCustomer().getId());
        }


        if (wishlist.getProducts() != null) {
            wishlistDTO.setProductIds(
                wishlist.getProducts().stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()) 
            );
        }

        return wishlistDTO;
    }
}
