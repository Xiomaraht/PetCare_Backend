package com.edu.sena.Petcare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import com.edu.sena.Petcare.dto.EmployeeCreationDTO;
import com.edu.sena.Petcare.dto.EmployeeDTO;
import com.edu.sena.Petcare.dto.EmployeeResponseDTO;
import com.edu.sena.Petcare.dto.EmployeeUpdateDTO;
import com.edu.sena.Petcare.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> newEmployee(@RequestBody EmployeeCreationDTO CreationDTO) {
        EmployeeResponseDTO nuevoEmployee = employeeService.newEmployee(CreationDTO);
        return new ResponseEntity<>(nuevoEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        List<EmployeeDTO> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO findEmployee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(findEmployee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeUpdateDTO> updateEmployee(@PathVariable Long id,
            @RequestBody EmployeeUpdateDTO employeeUpdateDTO) {
        EmployeeUpdateDTO updatedEmployee = employeeService.updateEmployee(id, employeeUpdateDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
