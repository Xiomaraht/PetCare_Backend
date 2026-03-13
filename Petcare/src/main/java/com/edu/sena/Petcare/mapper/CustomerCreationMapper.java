package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.CustomerCreationDTO;
import com.edu.sena.Petcare.models.Customer;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerCreationMapper {
    
    private final ModelMapper modelMapper;

    public Customer toEntity(CustomerCreationDTO customerCreationDTO){
        return modelMapper.map(customerCreationDTO, Customer.class);
    }

    
    public void toEntity(CustomerCreationDTO customerCreationDTO, Customer customerExistente){
        modelMapper.map(customerCreationDTO, customerExistente);
    }

    public CustomerCreationDTO toDTO(Customer customer){
        return modelMapper.map(customer, CustomerCreationDTO.class);
    }
}
