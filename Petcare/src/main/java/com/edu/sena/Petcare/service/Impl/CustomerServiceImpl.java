package com.edu.sena.Petcare.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.edu.sena.Petcare.dto.CustomerCreationDTO;
import com.edu.sena.Petcare.dto.CustomerDTO;
import com.edu.sena.Petcare.models.Customer;
import com.edu.sena.Petcare.models.User;
import com.edu.sena.Petcare.models.DocumentType;
import com.edu.sena.Petcare.models.Neighborhood;
import com.edu.sena.Petcare.repository.CustomerRepository;
import com.edu.sena.Petcare.repository.UserRepository;
import com.edu.sena.Petcare.repository.DocumentTypeRepository;
import com.edu.sena.Petcare.repository.NeighborhoodRepository;
import com.edu.sena.Petcare.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final NeighborhoodRepository neighborhoodRepository;

    @Override
    public CustomerDTO crearCustomer(CustomerCreationDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        Customer customer = new Customer();
        customer.setName((user.getFirstName() != null ? user.getFirstName() : "") + " " + (user.getLastName() != null ? user.getLastName() : ""));
        customer.setEmail(user.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        customer.setDocumentNumber(dto.getDocumentNumber());
        customer.setUser(user);

        if (dto.getDocumentTypeId() != null) {
            DocumentType dt = documentTypeRepository.findById(dto.getDocumentTypeId()).orElse(null);
            customer.setDocumentType(dt);
        }

        if (dto.getNeighborhoodId() != null) {
            Neighborhood nb = neighborhoodRepository.findById(dto.getNeighborhoodId()).orElse(null);
            customer.setBarrioCliente(nb);
        }

        Customer saved = customerRepository.save(customer);

        return mapToDTO(saved);
    }

    @Override
    public List<CustomerDTO> obtenerCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> obtenerCustomerPorId(Long id) {
        return customerRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Override
    public CustomerDTO actualizarCustomer(Long id, CustomerDTO dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer no existe"));

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());

        return mapToDTO(customerRepository.save(customer));
    }

    @Override
    public void eliminarCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer no existe");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<CustomerDTO> obtenerCustomerPorUserId(Long userId) {
        return customerRepository.findByUser_Id(userId)
                .map(this::mapToDTO);
    }

    // Mapper Entity → DTO
    private CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setAddress(customer.getAddress());
        dto.setUserId(customer.getUser().getId());
        return dto;
    }
}
