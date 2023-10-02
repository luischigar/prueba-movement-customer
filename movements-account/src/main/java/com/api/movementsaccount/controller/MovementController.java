package com.api.movementsaccount.controller;

import com.api.movementsaccount.exception.MovementException;
import com.api.movementsaccount.exception.ResourceNotFoundException;
import com.api.movementsaccount.service.MovementService;
import com.api.movementsaccount.service.dto.MovementDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movements")
@CrossOrigin(origins = "*")
public class MovementController {
    private final MovementService movementService;
    @Autowired
    public MovementController(MovementService movementService){
        this.movementService = movementService;
    }
    @PostMapping
    public ResponseEntity<?> createMovement(@Valid @RequestBody MovementDto movementDto) throws MovementException, ResourceNotFoundException {
        return new ResponseEntity<>(movementService.createMovement(movementDto), HttpStatus.CREATED);
    }
}
