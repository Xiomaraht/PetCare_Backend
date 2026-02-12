package com.edu.sena.Petcare.Controller;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.sena.Petcare.dto.CustomerCreationDTO;
import com.edu.sena.Petcare.dto.CustomerDTO;
import com.edu.sena.Petcare.Exceptions.ResourceNotFoundException;
import com.edu.sena.Petcare.service.CustomerService;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService objetoCustomerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> crearCustomer(@RequestBody CustomerCreationDTO creationDTO) throws BadRequestException{
        //por medio del objeto de service, usamos el metodo que creamos para guardar la nueva entidad
        CustomerDTO nuevoCustomer = objetoCustomerService.crearCustomer(creationDTO);
        return new ResponseEntity<>(nuevoCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> obtenerClientes(){
        List<CustomerDTO> customers = objetoCustomerService.obtenerCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> buscarClientePorId(@PathVariable Long id){
        Optional<CustomerDTO> customerFound = objetoCustomerService.obtenerCustomerPorId(id);
        if(customerFound.isPresent()){
            return new ResponseEntity<>(customerFound.get(), HttpStatus.OK);
        }else{
            throw new ResourceNotFoundException("Customer no encontrado por ID");
        }
        
    }

    @GetMapping("/user/{userId}") 
    public ResponseEntity<CustomerDTO> buscarClientePorUserId(@PathVariable Long userId){
        Optional<CustomerDTO> customerFound = objetoCustomerService.obtenerCustomerPorUserId(userId);
        if(customerFound.isPresent()){
            return new ResponseEntity<>(customerFound.get(), HttpStatus.OK);
        }else{
            throw new ResourceNotFoundException("Customer no encontrado para el User ID: " + userId);
        }
    }

    @PutMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<CustomerDTO> actualizarCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerParameter){
        try{
            CustomerDTO customerActualizado = objetoCustomerService.actualizarCustomer(id, customerParameter);
            if(customerActualizado != null){
                return new ResponseEntity<>(customerActualizado, HttpStatus.OK);
            }else{
                throw new Exception("Customer no encontrado para actualizar");
            }
        }catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCustomer(@PathVariable Long id){
        try{
            objetoCustomerService.eliminarCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}