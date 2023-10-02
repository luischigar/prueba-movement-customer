package com.api.movementsaccount.service;

import com.api.movementsaccount.exception.MovementException;
import com.api.movementsaccount.exception.ResourceNotFoundException;
import com.api.movementsaccount.service.dto.MovementDto;
import com.api.movementsaccount.service.dto.PaginationDto;
import com.api.movementsaccount.service.dto.PagingDto;

import java.time.LocalDate;

public interface MovementService {
    MovementDto createMovement(MovementDto movementDto) throws ResourceNotFoundException, MovementException;
    PaginationDto getReportMovement(LocalDate dateInit, LocalDate dateFin, PagingDto pagingDto) throws ResourceNotFoundException;
}
