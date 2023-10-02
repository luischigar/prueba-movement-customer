package com.api.movementsaccount.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementDto {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 10)
    private String accountNumber;
    @NotNull
    @NotBlank
    @Size(min = 1,max = 1)
    private String idTmo;
    private LocalDateTime date;
    @NotNull
    private Double value;
    private Double balance;
}
