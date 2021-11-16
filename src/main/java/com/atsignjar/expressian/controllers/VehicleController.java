package com.atsignjar.expressian.controllers;

import com.atsignjar.expressian.models.Vehicle;
import com.atsignjar.expressian.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/cars")
public class VehicleController {


        @Autowired
        private VehicleRepository repository;

        @GetMapping
        public @ResponseBody
        List<Vehicle> getCars(){return repository.findAll();}


        @PostMapping
        public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle newVehicle){
            return new ResponseEntity<>(repository.save(newVehicle), HttpStatus.CREATED);
        }
}
