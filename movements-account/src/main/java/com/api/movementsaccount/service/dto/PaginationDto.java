package com.api.movementsaccount.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDto {
    private Integer totalPages;
    private Long totalElements;
    private List<ReportMovement> reportMovements;
}
