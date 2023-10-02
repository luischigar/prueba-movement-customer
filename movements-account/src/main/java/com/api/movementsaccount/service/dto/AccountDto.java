package com.api.movementsaccount.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String idCue;
    @NotNull
    @NotBlank
    private String idCli;
    @NotNull
    @NotBlank
    @Size(max = 1,min = 1)
    private String idTcu;
    @NotNull
    @NotBlank
    @Size(max = 10)
    private String accountNumber;
    @NotNull
    private Double initialBalance;
    private Boolean state;
}
