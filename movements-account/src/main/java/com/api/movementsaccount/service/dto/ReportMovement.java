package com.api.movementsaccount.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportMovement {
    private LocalDateTime date;
    private String customer;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean state;
    private String movementType;
    private Double movement;
    private Double balance;
}
