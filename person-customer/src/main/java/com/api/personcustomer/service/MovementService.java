package com.api.personcustomer.service;

import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.service.dto.MovementDto;
import com.api.personcustomer.service.dto.PaginationDto;
import com.api.personcustomer.service.dto.PagingDto;

import java.time.LocalDate;

public interface MovementService {
    MovementDto createMovement(MovementDto movementDto) throws ResourceNotFoundException;
    PaginationDto getReportMovement(LocalDate dateInit, LocalDate dateFin, PagingDto pagingDto) throws ResourceNotFoundException;
}
