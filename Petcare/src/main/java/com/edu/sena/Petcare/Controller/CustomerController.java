package com.edu.sena.Petcare.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.edu.sena.Petcare.dto.CustomerCreationDTO;
import com.edu.sena.Petcare.dto.CustomerDTO;
import com.edu.sena.Petcare.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerCreationDTO customerCreationDTO) {
        return ResponseEntity.ok(customerService.crearCustomer(customerCreationDTO));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.obtenerCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.obtenerCustomerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.actualizarCustomer(id, customerDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CustomerDTO> getCustomerByUserId(@PathVariable Long userId) {
        return customerService.obtenerCustomerPorUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/clinic/{clinicId}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByClinic(@PathVariable Long clinicId) {
        return ResponseEntity.ok(customerService.obtenerClientesPorClinica(clinicId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.eliminarCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
