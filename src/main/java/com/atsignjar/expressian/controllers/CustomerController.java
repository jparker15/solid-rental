package com.atsignjar.expressian.controllers;

import com.atsignjar.expressian.models.Customer;
import com.atsignjar.expressian.models.Vehicle;
import com.atsignjar.expressian.repositories.CustomerRepository;
import com.atsignjar.expressian.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/customers")

public class CustomerController {

        @Autowired
        private CustomerRepository repository;

        //Read All
        @GetMapping
        public @ResponseBody
        List<Customer> getCustomers(){return repository.findAll();}

        //Read by ID
        @GetMapping("/{id}")
        public @ResponseBody Customer getCustomerId(@PathVariable Long id){
            return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

        //Create One
        @PostMapping
        public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer){
            return new ResponseEntity<>(repository.save(newCustomer), HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public @ResponseBody Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updates){
                Customer customer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

                if(updates.getAge() != null) customer.setAge(updates.getAge());
                if(updates.getEmail() != null) customer.setEmail(updates.getEmail());
                if(updates.getName() != null) customer.setName(updates.getName());

                return repository.save(customer);
        }

        @DeleteMapping("/id")
        public ResponseEntity<String> destroyCustomer(@PathVariable Long id){
                repository.deleteById(id);
                return null;
        }

}
