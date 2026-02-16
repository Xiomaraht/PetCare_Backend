package com.edu.sena.Petcare.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.edu.sena.Petcare.dto.CustomerCreationDTO;
import com.edu.sena.Petcare.dto.CustomerDTO;
import com.edu.sena.Petcare.Exceptions.ResourceNotFoundException;
import com.edu.sena.Petcare.service.CustomerService;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService objetoCustomerService;

}