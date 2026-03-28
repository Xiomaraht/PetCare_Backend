package com.edu.sena.Petcare.service.impl;

import com.edu.sena.Petcare.dto.EmployeeCreationDTO;
import com.edu.sena.Petcare.dto.EmployeeDTO;
import com.edu.sena.Petcare.dto.EmployeeResponseDTO;
import com.edu.sena.Petcare.dto.EmployeeUpdateDTO;
import com.edu.sena.Petcare.repository.EmployeeRepository;
import com.edu.sena.Petcare.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO newEmployee(EmployeeCreationDTO employeeCreationDTO) {
        // implementación pendiente
        return new EmployeeResponseDTO();
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        // implementación pendiente
        return List.of();
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        // implementación pendiente
        return new EmployeeDTO();
    }
    
    @Override
    public EmployeeUpdateDTO updateEmployee(Long id, EmployeeUpdateDTO employeeUpdateDTO) {
        // implementación pendiente
        return employeeUpdateDTO;
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Empleado no existe");
        }
        employeeRepository.deleteById(id);
    }
}
