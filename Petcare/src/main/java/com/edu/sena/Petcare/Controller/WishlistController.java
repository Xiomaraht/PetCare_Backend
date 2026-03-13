package com.edu.sena.Petcare.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.sena.Petcare.dto.WishlistDTO;
import com.edu.sena.Petcare.service.WishlistService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/wishlists")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<WishlistDTO> crearWishlist(@RequestBody WishlistDTO wishlistDTO){
        WishlistDTO wishlistNuevo = wishlistService.newWishlist(wishlistDTO);
        return new ResponseEntity<>(wishlistNuevo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WishlistDTO>> obtenerWishlists(){
        List<WishlistDTO> wishlists = wishlistService.getAllWishlists();
        return new ResponseEntity<>(wishlists, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistDTO> obtenerWishlistPorId(@PathVariable Long id){
        WishlistDTO wishlistBuscada = wishlistService.getSpecificWishlist(id);
        return new ResponseEntity<>(wishlistBuscada, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WishlistDTO> actualizarWishlist(@PathVariable Long id, @RequestBody WishlistDTO wishlistDTO){
        WishlistDTO actulizandinWishlist = wishlistService.updateWishlist(id, wishlistDTO);
        return new ResponseEntity<>(actulizandinWishlist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarWishlist(@PathVariable Long id){
        wishlistService.deleteWishlist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
