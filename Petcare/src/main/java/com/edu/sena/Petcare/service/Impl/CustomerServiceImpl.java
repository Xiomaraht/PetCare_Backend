package com.edu.sena.Petcare.service.Impl;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;
import com.edu.sena.Petcare.dto.CustomerCreationDTO;
import com.edu.sena.Petcare.dto.CustomerDTO;
import com.edu.sena.Petcare.Exceptions.BusinessException;
import com.edu.sena.Petcare.Exceptions.DuplicateResourceException;
import com.edu.sena.Petcare.Exceptions.ResourceNotFoundException;
import com.edu.sena.Petcare.mapper.CustomerCreationMapper;
import com.edu.sena.Petcare.mapper.CustomerMapper;
import com.edu.sena.Petcare.models.Customer;
import com.edu.sena.Petcare.models.DocumentType;
import com.edu.sena.Petcare.models.Neighborhood;
import com.edu.sena.Petcare.models.User;
import com.edu.sena.Petcare.repository.CustomerRepository;
import com.edu.sena.Petcare.repository.DocumentTypeRepository;
import com.edu.sena.Petcare.repository.NeighborhoodRepository;
import com.edu.sena.Petcare.repository.UserRepository;
import com.edu.sena.Petcare.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository objetoCustomerRepository;
    private final UserRepository objetoUserRepository;
    private final CustomerMapper customerMapper;
    private final CustomerCreationMapper customerCreationMapper;
    private final DocumentTypeRepository documentTypeRepository;
    private final NeighborhoodRepository neighborhoodRepository;

    
    //Este metodo es especial, pido una respuest en un DTO especifico creado para devolver cierto datos
    @Override
    @Transactional
    public CustomerDTO crearCustomer(CustomerCreationDTO creationDTO) {
        //Validacion de la existencia del usuario
        User existeUser = objetoUserRepository.findById(creationDTO.getUserId())
            .orElseThrow(()-> new ResourceNotFoundException("Usuario con id " + creationDTO.getUserId() + " no existe"));
        
        //validacion para comprobar que el user no esta asociado.
        if(existeUser.getCustomer() != null || existeUser.getEmployee() != null){
            throw new BusinessException("Error, usuario asociado a Empleado o Cliente");
        }

        //validacion de unicidad de capo DocumentType y DocumentNumber
        if(objetoCustomerRepository.existsByDocumentNumberAndDocumentTypeId(creationDTO.getDocumentNumber(), creationDTO.getDocumentTypeId())){
            throw new DuplicateResourceException("Ya existe un cliente con ese tipo y numero de documento");
        }

        //Mapeo 
        Customer customer = customerCreationMapper.toEntity(creationDTO);
        customer.setUser(existeUser);
        
        //Validamos que el ingreso del DocumentType sea correcto
        DocumentType doc = documentTypeRepository.findById(creationDTO.getDocumentTypeId())
                .orElseThrow(()-> new ResourceNotFoundException("No se encontró el tipo de documento con ID: "+creationDTO.getDocumentTypeId()));
        customer.setDocumentType(doc);  
        
        //validamos que el barrio que sse ingreso, si existe
        Neighborhood barrio = neighborhoodRepository.findById(creationDTO.getNeighborhoodId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el barrio con ID: "+creationDTO.getNeighborhoodId()));
        customer.setBarrioCliente(barrio);
        
        //Creamos una entidad para guardar
        Customer saveCustomer = objetoCustomerRepository.save(customer);
        CustomerDTO salida = customerMapper.toDTO(saveCustomer);
        salida.setDocumenTypeName(saveCustomer.getDocumentType().getName());
        salida.setNeighborhoodName(saveCustomer.getBarrioCliente().getName());

        return salida;
    }

    @Override
    public List<CustomerDTO> obtenerCustomers() {
        List<Customer> customers = objetoCustomerRepository.findAll();
        //recorremos cada customer, lo convierto a DTO y finalmente lo retorno a una lista
        return customers.stream().map(customerMapper::toDTO).toList();
    }

    @Override
    public Optional<CustomerDTO> obtenerCustomerPorId(Long id) { 
        Optional<Customer> customer = objetoCustomerRepository.findById(id);
        return customer.map(customerMapper:: toDTO);
    }

    @Override
    @SneakyThrows //No se deberia usar, pero por ahora funcionará, mas adelante metere mas buenas practicas
    public CustomerDTO actualizarCustomer(Long id, CustomerDTO customerDTO){
        Customer customerExistente = objetoCustomerRepository.findById(id)
            .orElseThrow(() -> new Exception("Cliente no encontrado"));
        customerExistente.setAddress(customerDTO.getAddress());
        customerExistente.setAddressDetail(customerDTO.getAddressDetail());
        customerExistente.setPhone(customerDTO.getPhone());

        if(customerDTO.getUser() != null && customerDTO.getUser().getId() != null){
            User userExistente = objetoUserRepository.findById(customerDTO.getUser().getId()) //valido si previamente se creo al usuario que esta vinculado al customer
                    .orElseThrow(() -> new Exception("Usuario de id no existente, para actualizar"));
            customerExistente.setUser(userExistente);
        }
        Customer customerActualizado = objetoCustomerRepository.save(customerExistente);
        return customerMapper.toDTO(customerActualizado);

        
    }

    @Override
    public void eliminarCustomer(Long id) {
        objetoCustomerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer no econtrado para su eliminacion"));
        objetoCustomerRepository.deleteById(id);    
    }

    @Override
    public Optional<CustomerDTO> obtenerCustomerPorUserId(Long userId) {
        // Usa el nuevo método de repositorio
        Optional<Customer> customer = objetoCustomerRepository.findByUser_Id(userId); 
        return customer.map(customerMapper::toDTO);
    }

}