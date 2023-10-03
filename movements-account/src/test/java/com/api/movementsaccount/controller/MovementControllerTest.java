package com.api.movementsaccount.controller;

import com.api.movementsaccount.exception.MovementException;
import com.api.movementsaccount.exception.ResourceNotFoundException;
import com.api.movementsaccount.message.MessageSuccess;
import com.api.movementsaccount.service.MovementService;
import com.api.movementsaccount.service.dto.MovementDto;
import com.api.movementsaccount.service.dto.PaginationDto;
import com.api.movementsaccount.service.dto.PagingDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class MovementControllerTest {
    @Mock
    private MovementService movementService;
    @InjectMocks
    private MovementController movementController;
    @Test
    void createMovement() throws MovementException, ResourceNotFoundException {
        Mockito.when(movementService.createMovement(Mockito.any(MovementDto.class))).thenReturn(new MovementDto());
        ResponseEntity<?> responseEntity = movementController.createMovement(new MovementDto());
        Assert.isTrue(responseEntity.getStatusCode() == HttpStatus.CREATED, MessageSuccess.SUCCESS);
    }
    @Test
    void getReportMovement() throws ResourceNotFoundException {
        Mockito.when(movementService.getReportMovement(Mockito.any(LocalDate.class),Mockito.any(LocalDate.class),Mockito.any(PagingDto.class))).thenReturn(new PaginationDto());
        ResponseEntity<?> responseEntity = movementController.getReportMovement(LocalDate.now(),LocalDate.now(),new PagingDto());
        Assert.isTrue(responseEntity.getStatusCode() == HttpStatus.OK, MessageSuccess.SUCCESS);
    }
}
