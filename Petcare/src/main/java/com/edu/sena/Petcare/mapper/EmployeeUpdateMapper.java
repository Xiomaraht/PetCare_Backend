package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.EmployeeUpdateDTO;
import com.edu.sena.Petcare.models.Employee;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeUpdateMapper {
    private final ModelMapper modelMapper;

    public Employee toEntity(EmployeeUpdateDTO employeeUpdateDTO){
        return modelMapper.map(employeeUpdateDTO, Employee.class);
    }

    public void toEntity(EmployeeUpdateDTO employeeUpdateDTO, Employee employee){
        modelMapper.map(employeeUpdateDTO, employee);
    }

    public EmployeeUpdateDTO toDTO(Employee employee){
        return modelMapper.map(employee, EmployeeUpdateDTO.class);
    }
}
