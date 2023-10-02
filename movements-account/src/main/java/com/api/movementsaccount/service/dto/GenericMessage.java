package com.api.movementsaccount.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericMessage {
    private String code;
    private String message;
}
