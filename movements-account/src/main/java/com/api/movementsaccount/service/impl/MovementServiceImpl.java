package com.api.movementsaccount.service.impl;

import com.api.movementsaccount.exception.MovementException;
import com.api.movementsaccount.exception.ResourceNotFoundException;
import com.api.movementsaccount.model.Account;
import com.api.movementsaccount.model.Movement;
import com.api.movementsaccount.model.MovementType;
import com.api.movementsaccount.repository.MovementRepository;
import com.api.movementsaccount.repository.repositorys.ReportMovementRepository;
import com.api.movementsaccount.service.AccountService;
import com.api.movementsaccount.service.MovementService;
import com.api.movementsaccount.service.dto.AccountDto;
import com.api.movementsaccount.service.dto.MovementDto;
import com.api.movementsaccount.service.dto.PaginationDto;
import com.api.movementsaccount.service.dto.PagingDto;
import com.api.movementsaccount.service.mapper.MovementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements MovementService {
    private final MovementRepository movementRepository;
    private final AccountService accountService;
    private final MovementMapper movementMapper;
    private final ReportMovementRepository reportMovementRepository;
    @Autowired
    public MovementServiceImpl(MovementRepository movementRepository,
                               AccountService accountService,
                               MovementMapper movementMapper,
                               ReportMovementRepository reportMovementRepository){
        this.movementRepository = movementRepository;
        this.accountService = accountService;
        this.movementMapper = movementMapper;
        this.reportMovementRepository = reportMovementRepository;
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

    @Override
    public PaginationDto getReportMovement(LocalDate dateInit, LocalDate dateFin, PagingDto pagingDto) throws ResourceNotFoundException {
        validateDate(dateInit,dateFin);
        return pageTransformIntoMovementPagination(
                reportMovementRepository.getMovementByClientAndDateAndPage(dateInit,dateFin,pagingDto)
        );
    }
    private PaginationDto pageTransformIntoMovementPagination(Page<Movement> pagedResult){
        PaginationDto paginationTo = new PaginationDto(pagedResult.getTotalPages(), pagedResult.getTotalElements(),new ArrayList<>());
        paginationTo.setReportMovements(pagedResult.getContent().stream()
                .map(movementMapper::movementToReportMovement)
                .collect(Collectors.toList()));
        return paginationTo;
    }
    private void validateDate(LocalDate dateInit, LocalDate dateFin) throws ResourceNotFoundException {
        if(dateInit != null && dateFin != null){
            if(dateInit.isAfter(dateFin)){
                throw new ResourceNotFoundException("Fecha inicio es mayor");
            }
        }
        if((dateInit == null && dateFin != null) ||(dateInit != null && dateFin == null)){
            throw new ResourceNotFoundException("Fechas incompleta");
        }
    }
}
