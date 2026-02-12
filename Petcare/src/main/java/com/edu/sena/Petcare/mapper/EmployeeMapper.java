package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.EmployeeDTO;
import com.edu.sena.Petcare.models.Employee;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final ModelMapper modelMapper;

    public Employee toEntity(EmployeeDTO employeeDTO){
        return modelMapper.map(employeeDTO, Employee.class);
    }

    public void toEntity(EmployeeDTO employeeDTO, Employee employee){
        modelMapper.map(employeeDTO, employee);
    }

    public EmployeeDTO toDTO(Employee employee){
        return modelMapper.map(employee, EmployeeDTO.class);
    }
}
