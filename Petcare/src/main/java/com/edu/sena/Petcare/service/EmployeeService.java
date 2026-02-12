package com.edu.sena.Petcare.service;

import java.util.List;

import com.edu.sena.Petcare.dto.EmployeeCreationDTO;
import com.edu.sena.Petcare.dto.EmployeeDTO;
import com.edu.sena.Petcare.dto.EmployeeResponseDTO;
import com.edu.sena.Petcare.dto.EmployeeUpdateDTO;

public interface EmployeeService {

    EmployeeResponseDTO newEmployee(EmployeeCreationDTO employeeCreationDTO);

    List<EmployeeDTO> getEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeUpdateDTO updateEmployee(Long id, EmployeeUpdateDTO employeeUpdateDTO);

    void deleteEmployee(Long id);
}