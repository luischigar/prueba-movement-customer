package com.api.personcustomer.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingDto {
    @NotNull
    @Min(0)
    private Integer page;
    @NotNull
    @Size(min = 1)
    private Integer size;
    @NotBlank
    private String filter;
}
