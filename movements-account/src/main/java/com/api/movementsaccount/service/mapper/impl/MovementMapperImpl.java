package com.api.movementsaccount.service.mapper.impl;

import com.api.movementsaccount.model.Movement;
import com.api.movementsaccount.service.dto.MovementDto;
import com.api.movementsaccount.service.mapper.MovementMapper;
import org.springframework.stereotype.Component;

@Component
public class MovementMapperImpl implements MovementMapper {
    @Override
    public MovementDto movementToMovementDto(Movement movement) {
        return new MovementDto(movement.getAccount().getAccountNumber(),
                movement.getMovementType().getIdTmo(),
                movement.getDate(),
                movement.getValue(),
                movement.getBalance()
        );
    }
}
