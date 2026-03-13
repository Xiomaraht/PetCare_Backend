 package com.edu.sena.Petcare.controller;

import com.edu.sena.Petcare.dto.PaymentMethodDTO;
import com.edu.sena.Petcare.service.PaymentMethodService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-method")
@CrossOrigin(origins = "*")
public class PaymentMethodController {

    private final PaymentMethodService service;

    public PaymentMethodController(PaymentMethodService service) {
        this.service = service;
    }

    @GetMapping
    public List<PaymentMethodDTO> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PaymentMethodDTO getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    public PaymentMethodDTO create(@RequestBody PaymentMethodDTO dto){
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public PaymentMethodDTO update(@PathVariable Long id, @RequestBody PaymentMethodDTO dto){
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
