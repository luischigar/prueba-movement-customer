package com.api.movementsaccount.service.mapper;

import com.api.movementsaccount.model.Movement;
import com.api.movementsaccount.service.dto.MovementDto;
import org.mapstruct.Mapper;

@Mapper
public interface MovementMapper {
    MovementDto movementToMovementDto(Movement movement);
}
