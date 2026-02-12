package com.edu.sena.Petcare.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edu.sena.Petcare.dto.EmployeeCreationDTO;
import com.edu.sena.Petcare.dto.EmployeeDTO;
import com.edu.sena.Petcare.dto.EmployeeResponseDTO;
import com.edu.sena.Petcare.dto.EmployeeUpdateDTO;
import com.edu.sena.Petcare.Exceptions.BusinessException;
import com.edu.sena.Petcare.Exceptions.DuplicateResourceException;
import com.edu.sena.Petcare.Exceptions.ResourceNotFoundException;
import com.edu.sena.Petcare.mapper.EmployeeCreationMapper;
import com.edu.sena.Petcare.mapper.EmployeeMapper;
import com.edu.sena.Petcare.mapper.EmployeeResponseMapper;
import com.edu.sena.Petcare.mapper.EmployeeUpdateMapper;
import com.edu.sena.Petcare.models.DocumentType;
import com.edu.sena.Petcare.models.Employee;
import com.edu.sena.Petcare.models.User;
import com.edu.sena.Petcare.repository.DocumentTypeRepository;
import com.edu.sena.Petcare.repository.EmployeeRepository;
import com.edu.sena.Petcare.repository.UserRepository;
import com.edu.sena.Petcare.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeCreationMapper employeeCreationMapper;
    private final EmployeeResponseMapper employeeResponseMapper;
    private final EmployeeUpdateMapper employeeUpdateMapper;
    private final UserRepository userRepository;
    private final DocumentTypeRepository documentTypeRepository;

    @Override
    @Transactional
    public EmployeeResponseDTO newEmployee(EmployeeCreationDTO creationDTO) {

        //Validaré sí el usuario existe, sino pos yuca
        User existeUser = userRepository.findById(creationDTO.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("No se ha encotrado el usuario con id " +creationDTO.getUserId()+". Fallo de Asignacion"));
        
        //Puede existir pero puede que no este disponible, que ya este relacionado con un Employee o Customer, validaré eso
        if(existeUser.getEmployee() != null || existeUser.getCustomer() != null){
            throw new BusinessException("El usuario con ID " + creationDTO.getUserId() + " ya está asignado a un rol (empleado o cliente).");
        }

        //Que tal si meten un tipo de documento que no exista? asi es, validaremos eso tambien
        DocumentType documenType = documentTypeRepository.findById(creationDTO.getDocumentTypeId())
                .orElseThrow(()-> new ResourceNotFoundException("No se ha podido encontrar el tipo de documento con ID: "+creationDTO.getDocumentTypeId()));
        
        //Ahora, validare que no exista un empleado con su uniqueConstraint tipo y numero de documento;
        Optional<Employee> employeeExist = employeeRepository.findByDocumentType_IdAndDocumentNumber(creationDTO.getDocumentTypeId(), creationDTO.getDocumentNumber());
        if(employeeExist.isPresent()){ // -> Eh, uso if por que estoy manejando un optional y no puedo usar el .orElseThrow
            throw new DuplicateResourceException("Un empleado con ese tipo y número de documento ya existe.");
        }

        //Mapeare la creacion a la entidad que será guardada
        Employee newEmployee = employeeCreationMapper.toEntity(creationDTO);

        //Por ultimo, asigno(o Mappeo) las entidades validadas a los campos disponibles en la entidad
        newEmployee.setUser(existeUser);
        newEmployee.setDocumentType(documenType);

        //Guardo la entidad en un nuevo objeto, solo por que lo mapeare despues para generar un DTO de tipo response de Employee
        Employee savedEmployee = employeeRepository.save(newEmployee);

        //Retorno y mapeo a la entidad de retorno de Response
        return employeeResponseMapper.toDTO(savedEmployee);


    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employeeMapper::toDTO).toList();
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) { 
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al empleado con ID: " + id));
        return employeeMapper.toDTO(employee); 
    }

    @Override
    public EmployeeUpdateDTO updateEmployee(Long id, EmployeeUpdateDTO employeeUpdateDTO) {
        //Verificamos que exista el empleado
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("No se ha encontrado el cliente a actualizar"));
        
            employeeUpdateMapper.toEntity(employeeUpdateDTO, employee);

            employeeRepository.save(employee);

            return employeeUpdateMapper.toDTO(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("No se ha encontrado el empleado con ID "+id+" para su eliminacion");
        }
    }

}