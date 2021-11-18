package com.atsignjar.expressian.controllers;

import com.atsignjar.expressian.models.Customer;
import com.atsignjar.expressian.models.Vehicle;
import com.atsignjar.expressian.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {


        @Autowired
        private VehicleRepository repository;

        //Read All
        @GetMapping
        public @ResponseBody
        List<Vehicle> getVehicles(){return repository.findAll();}

        //Read by ID
        @GetMapping("/{id}")
        public @ResponseBody Vehicle getVehicleId(@PathVariable Long id){
                return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

        //Create One
        @PostMapping
        public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle newVehicle){
            return new ResponseEntity<>(repository.save(newVehicle), HttpStatus.CREATED);
        }

        //Update by ID
        @PutMapping("/{id}")
        public @ResponseBody
        Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle updates){
                Vehicle vehicle = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

                if(updates.getMake() != null) vehicle.setMake(updates.getMake());
                if(updates.getModel() != null) vehicle.setModel(updates.getModel());
                if(updates.getYear() > 0) vehicle.setYear(updates.getYear());

                return repository.save(vehicle);
        }
        //Delete by ID
        @DeleteMapping("/id")
        public ResponseEntity<String> destroyVehicle(@PathVariable Long id){
                repository.deleteById(id);
                return null;
        }
}
