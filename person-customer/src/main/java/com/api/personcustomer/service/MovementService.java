package com.api.personcustomer.service;

import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.service.dto.MovementDto;

public interface MovementService {
    MovementDto createMovement(MovementDto movementDto) throws ResourceNotFoundException;
}
