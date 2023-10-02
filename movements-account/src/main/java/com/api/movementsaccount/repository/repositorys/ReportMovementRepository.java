package com.api.movementsaccount.repository.repositorys;

import com.api.movementsaccount.model.Movement;
import com.api.movementsaccount.service.dto.PagingDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface ReportMovementRepository {
    Page<Movement> getMovementByClientAndDateAndPage(LocalDate dateInit, LocalDate dateFin, PagingDto pagingDto);
}
