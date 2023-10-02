package com.api.movementsaccount.service;

import com.api.movementsaccount.exception.MovementException;
import com.api.movementsaccount.exception.ResourceNotFoundException;
import com.api.movementsaccount.service.dto.MovementDto;

public interface MovementService {
    MovementDto createMovement(MovementDto movementDto) throws ResourceNotFoundException, MovementException;
}
