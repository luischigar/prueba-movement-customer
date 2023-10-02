package com.api.movementsaccount.service.impl;

import com.api.movementsaccount.exception.MovementException;
import com.api.movementsaccount.exception.ResourceNotFoundException;
import com.api.movementsaccount.model.Account;
import com.api.movementsaccount.model.Movement;
import com.api.movementsaccount.model.MovementType;
import com.api.movementsaccount.repository.MovementRepository;
import com.api.movementsaccount.service.AccountService;
import com.api.movementsaccount.service.MovementService;
import com.api.movementsaccount.service.dto.AccountDto;
import com.api.movementsaccount.service.dto.MovementDto;
import com.api.movementsaccount.service.mapper.MovementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MovementServiceImpl implements MovementService {
    private final MovementRepository movementRepository;
    private final AccountService accountService;
    private final MovementMapper movementMapper;
    @Autowired
    public MovementServiceImpl(MovementRepository movementRepository,
                               AccountService accountService,
                               MovementMapper movementMapper){
        this.movementRepository = movementRepository;
        this.accountService = accountService;
        this.movementMapper = movementMapper;
    }
    @Override
    @Transactional
    public MovementDto createMovement(MovementDto movementDto) throws ResourceNotFoundException, MovementException {
        AccountDto accountDto = accountService.updateInitialBalance(movementDto);
        return movementMapper.movementToMovementDto(
                movementRepository.save(
                        new Movement(null,
                                new Account(accountDto.getIdCue()),
                                new MovementType(movementDto.getIdTmo()),
                                LocalDateTime.now(),
                                movementDto.getValue(),
                                accountDto.getInitialBalance()
                        )
                )
        );
    }
}
