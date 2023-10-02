package com.api.personcustomer.controller;

import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.service.MovementService;
import com.api.personcustomer.service.dto.MovementDto;
import com.api.personcustomer.service.dto.PagingDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    public ResponseEntity<?> createMovement(@Valid @RequestBody MovementDto movementDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(movementService.createMovement(movementDto), HttpStatus.CREATED);
    }
    @PostMapping("/report")
    public ResponseEntity<?> getReportMovement(LocalDate dateInit, LocalDate dateFin, @RequestBody PagingDto pagingDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(movementService.getReportMovement(dateInit,dateFin,pagingDto),HttpStatus.OK);
    }
}
