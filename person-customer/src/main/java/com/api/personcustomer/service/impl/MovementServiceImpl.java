package com.api.personcustomer.service.impl;

import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.service.MovementService;
import com.api.personcustomer.service.dto.GenericMessage;
import com.api.personcustomer.service.dto.MovementDto;
import com.api.personcustomer.service.dto.PaginationDto;
import com.api.personcustomer.service.dto.PagingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
public class MovementServiceImpl implements MovementService {
    @Value("${movements-account.url.service}")
    private String baseUrl;
    private WebClient.Builder webClientBuilder;
    @Autowired
    public MovementServiceImpl(WebClient.Builder webClientBuilder){
        this.webClientBuilder = webClientBuilder;
    }
    @Override
    public MovementDto createMovement(MovementDto movementDto) throws ResourceNotFoundException {
        return createMovementUrlService(movementDto);
    }

    @Override
    public PaginationDto getReportMovement(LocalDate dateInit, LocalDate dateFin, PagingDto pagingDto) throws ResourceNotFoundException {
        validateDate(dateInit,dateFin);
        return getReportMovementUrlService(dateInit, dateFin, pagingDto);
    }
    private void validateDate(LocalDate dateInit, LocalDate dateFin) throws ResourceNotFoundException {
        if(dateInit != null && dateFin != null){
            if(dateInit.isAfter(dateFin)){
                throw new ResourceNotFoundException("Fecha inicio es mayor");
            }
        }
        if((dateInit == null && dateFin != null) ||(dateInit != null && dateFin == null)){
            throw new ResourceNotFoundException("Fechas incompleta");
        }
    }

    private MovementDto createMovementUrlService(MovementDto movementDto) throws ResourceNotFoundException {
        try {
            return webClientBuilder.build().post()
                    .uri(baseUrl+"/movements-account/movements")
                    .body(Mono.just(movementDto),MovementDto.class)
                    .retrieve()
                    .onStatus(HttpStatus.EXPECTATION_FAILED::equals, response ->
                            response.bodyToMono(GenericMessage.class).flatMap(error ->
                                    Mono.error(new Exception(error.getMessage()))
                            )
                    )
                    .bodyToMono(MovementDto.class)
                    .block();
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
    private PaginationDto getReportMovementUrlService(LocalDate dateInit, LocalDate dateFin,PagingDto pagingDto) throws ResourceNotFoundException {
        try {
            return webClientBuilder.build().post()
                    .uri(baseUrl+"/movements-account/movements/report?dateInit={date1}&dateFin={date2}"
                            ,dateInit==null?"":dateInit,dateFin==null?"":dateFin)
                    .body(Mono.just(pagingDto),PagingDto.class)
                    .retrieve()
                    .onStatus(HttpStatus.EXPECTATION_FAILED::equals, response ->
                            response.bodyToMono(GenericMessage.class).flatMap(error ->
                                    Mono.error(new Exception(error.getMessage()))
                            )
                    )
                    .bodyToMono(PaginationDto.class)
                    .block();
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
