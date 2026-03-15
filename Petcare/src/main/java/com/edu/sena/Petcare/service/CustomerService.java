package com.edu.sena.Petcare.service;

import java.util.List;
import java.util.Optional;

import com.edu.sena.Petcare.dto.CustomerCreationDTO;
import com.edu.sena.Petcare.dto.CustomerDTO;

public interface CustomerService {

    //funcion que crea un cliente, esto deberia devolver una entidad de tipo Customer
    CustomerDTO crearCustomer(CustomerCreationDTO customerCreationDTO);
    
    //Funcion que busca todos los clientes, esto deberia devolver una lista de entidades Customer
    List<CustomerDTO> obtenerCustomers();
    
    //Funcion Optional que busca obtener una entidad de tipo Customer por su id, se usa optional por que si no existe no queremos que ns devuelva un null 
    Optional<CustomerDTO> obtenerCustomerPorId(Long id);
    
    //Funcion que devuelve una entidad de tipo Customer para actualizarla, por tanto se le pasa el Id para buscarla, y luego la entidad actualizada para remplazar los datos
    CustomerDTO actualizarCustomer(Long id, CustomerDTO customerDTO);
    
    //Funcion que no retorna nada pues se busca un entidad de tipo Customer para eliminarla de la base de datos
    void eliminarCustomer(Long id);

    //Nuevo metodo para obtener el customer por el userId
    Optional<CustomerDTO> obtenerCustomerPorUserId(Long userId);

    List<CustomerDTO> obtenerClientesPorClinica(Long clinicId);
}
