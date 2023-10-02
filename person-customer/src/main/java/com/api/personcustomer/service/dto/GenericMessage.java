package com.api.personcustomer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class GenericMessage {
    private String code;
    private String message;
}
