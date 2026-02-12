package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.EmployeeCreationDTO;
import com.edu.sena.Petcare.models.Employee;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeCreationMapper {

    private final ModelMapper modelMapper;

    public Employee toEntity(EmployeeCreationDTO employeeCreationDTO){
        return modelMapper.map(employeeCreationDTO, Employee.class);
    }

    public void toEntity(EmployeeCreationDTO employeeCreationDTO, Employee employee){
        modelMapper.map(employeeCreationDTO, employee);
    }

    public EmployeeCreationDTO toDTO(Employee employee){
        return modelMapper.map(employee, EmployeeCreationDTO.class);
    }
}
