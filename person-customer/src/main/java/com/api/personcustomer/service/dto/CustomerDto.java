package com.api.personcustomer.service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String idCli;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 13)
    private String identification;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 1)
    private String idGen;
    @NotNull
    @NotBlank
    private String name;
    @Min(1)
    private Integer age;
    @NotNull
    @NotBlank
    private String address;
    @NotNull
    @NotBlank
    @Size(min = 0, max = 16)
    private String phone;
    @NotNull
    @NotBlank
    private String password;
    private Boolean state;
}
