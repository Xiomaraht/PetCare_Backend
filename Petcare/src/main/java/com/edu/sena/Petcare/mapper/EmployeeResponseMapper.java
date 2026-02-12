package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.EmployeeResponseDTO;
import com.edu.sena.Petcare.dto.UserGetDTO;
import com.edu.sena.Petcare.models.Employee;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeResponseMapper {

    private final ModelMapper modelMapper;
    private final UserGetMapper userGetMapper;

    public Employee toEntity(EmployeeResponseDTO employeeResponseDTO){
        return modelMapper.map(employeeResponseDTO, Employee.class);
    }

    public void toEntity(EmployeeResponseDTO employeeResponseDTO, Employee employee){
        modelMapper.map(employeeResponseDTO, employee);
    }

    public EmployeeResponseDTO toDTO(Employee employee) {
        // 2. MAPEAMOS LOS CAMPOS SIMPLES PRIMERO
        // ModelMapper se encargará de id, documentNumber, address, phone, etc.
        EmployeeResponseDTO dto = modelMapper.map(employee, EmployeeResponseDTO.class);

        // 3. USAMOS EL MAPPER ESPECÍFICO PARA EL OBJETO ANIDADO
        // Esto hace el mapeo explícito y seguro.
        UserGetDTO userDTO = userGetMapper.toDTO(employee.getUser());
        dto.setUser(userDTO);

        return dto;
    }
}
