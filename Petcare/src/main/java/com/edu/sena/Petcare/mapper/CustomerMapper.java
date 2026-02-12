package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.CustomerDTO;
import com.edu.sena.Petcare.models.Customer;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final ModelMapper modelMapper;

    // Conversion de DTO a Entity, llega del FRONT
    public Customer toEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, Customer.class);
    }

    
    public void toEntity(CustomerDTO customerDTO, Customer customerExistente){
        modelMapper.map(customerDTO, customerExistente);
    }

    public CustomerDTO toDTO(Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }
}
