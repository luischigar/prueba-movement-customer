package com.api.personcustomer.service.dto;

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
    @NotNull
    @NotBlank
    @Size(min = 0,max = 13)
    private String identification;
    private String idCli;
    @NotNull
    @NotBlank
    @Size(min = 1,max = 1)
    private String idTcu;
    @NotNull
    @NotBlank
    @Size(max = 10)
    private String accountNumber;
    @NotNull
    private Double initialBalance;
}
