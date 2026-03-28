package com.edu.sena.Petcare.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.sena.Petcare.dto.WishlistDTO;
import com.edu.sena.Petcare.mapper.WishlistMapper;
import com.edu.sena.Petcare.models.Customer;
import com.edu.sena.Petcare.models.Product;
import com.edu.sena.Petcare.models.Wishlist;
import com.edu.sena.Petcare.exception.ResourceNotFoundException;
import com.edu.sena.Petcare.repository.CustomerRepository;
import com.edu.sena.Petcare.repository.ProductRepository;
import com.edu.sena.Petcare.repository.WishlistRepository;
import com.edu.sena.Petcare.service.WishlistService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WishlistServiceImpl implements WishlistService {

    public final WishlistRepository wishlistRepository;
    public final WishlistMapper wishlistMapper;
    public final CustomerRepository customerRepository;
    public final ProductRepository productRepository;

    @Override
    @Transactional
    public WishlistDTO newWishlist(WishlistDTO wishlistDTO) {
        //Validaré la lista de productos para impedir que se guarde con un valor null
        if(wishlistDTO.getProductIds() == null || wishlistDTO.getProductIds().isEmpty()){
            throw new IllegalArgumentException("La lista de productos no puede estar vacía.");
        }
        //Valido ahora, si el customer existe, sino pos no se puede hacer na
        Customer buscandinCustomer = customerRepository.findById(wishlistDTO.getCustomerId())
                .orElseThrow(()-> new ResourceNotFoundException("No se encontró al cliente con id: "+wishlistDTO.getCustomerId()));
        
        //Traere una lista con los productos que por id se han encontrado
        List<Product> productos = productRepository.findAllById(wishlistDTO.getProductIds());  
        
        //Ahora valido que se hayan encontrado todos los productos, sino arrojamos error
        if(productos.size() != wishlistDTO.getProductIds().size()){
            throw new ResourceNotFoundException("No se encontró uno o mas productos");
        }

        //Creo una nueva entidad de Wishlist
        Wishlist wishlist = new Wishlist();
        wishlist.setCreateDate(LocalDateTime.now());
        wishlist.setCustomer(buscandinCustomer);
        wishlist.setProducts(productos);

        //Al haber una tabla intermedia, toca decirle a JPA que se porte serio y meta lo que es donde es.
        for (Product product : productos) {
            product.getWishlists().add(wishlist);
        }

        //Guardamos, salvamos, werever
        Wishlist wishlistGuardada = wishlistRepository.save(wishlist);

        //Haemos el retorno en DTO
        return wishlistMapper.toDTO(wishlistGuardada);
    }

    @Override
    public List<WishlistDTO> getAllWishlists() {
        List<Wishlist> wishlists = wishlistRepository.findAll();
        return wishlists.stream().map(wishlistMapper::toDTO).toList();
    }

    @Override
    public WishlistDTO getSpecificWishlist(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("No se encontro la lista de deseos por ID " + id));
        return wishlistMapper.toDTO(wishlist);
    }

    @Override
    @Transactional
    public WishlistDTO updateWishlist(Long id, WishlistDTO wishlistDTO) {
        //validamos que la nueva lista de productos no venga vacía
        if (wishlistDTO.getProductIds() == null || wishlistDTO.getProductIds().isEmpty()) {
            throw new IllegalArgumentException("La lista de productos no puede estar vacía para una actualización.");
        }

        // Buscamos la wishlist existente
        Wishlist wishlistExiste = wishlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado la lista de deseos con ID " + id + " para su actualización"));

        //Buscamos el customer, en caso claro, de que este haya cambiado, aunque eso no deberia ser posible
        Customer customer = customerRepository.findById(wishlistDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el cliente con ID " + wishlistDTO.getCustomerId()));
        
        //Volvemos a buscar y validar la lista de productos
        List<Product> newProducts = productRepository.findAllById(wishlistDTO.getProductIds());
        if (newProducts.size() != wishlistDTO.getProductIds().size()) {
            throw new ResourceNotFoundException("No se encontraron uno o más productos con los IDs proporcionados para la actualización.");
        }

        //Por ser una tabla intermedia primero hay que desvincular los productos que existian aqui
        for (Product oldProduct : wishlistExiste.getProducts()) {
            oldProduct.getWishlists().remove(wishlistExiste);
        }
        
        //Hay que limpiar la lista, osea eliminar todo
        wishlistExiste.getProducts().clear();

        //Ora si metemos la nueva lista de productos
        for (Product newProduct : newProducts) {
            wishlistExiste.getProducts().add(newProduct);
            newProduct.getWishlists().add(wishlistExiste);
        }
        
        // Actualizamos y guardamos
        wishlistExiste.setCustomer(customer);
        Wishlist updatedWishlist = wishlistRepository.save(wishlistExiste);
        //retornamos, You're my soda pop .. 
        return wishlistMapper.toDTO(updatedWishlist);
    }

    @Override
    public void deleteWishlist(Long id) {
        if(!wishlistRepository.existsById(id)){
            throw new ResourceNotFoundException("Lista de deseos con ID "+id+" No encontrado para su eliminacion");
        }
        wishlistRepository.deleteById(id);
    }
}
