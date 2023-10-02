package com.api.personcustomer.service.impl;

import com.api.personcustomer.exception.ResourceNotFoundException;
import com.api.personcustomer.service.MovementService;
import com.api.personcustomer.service.dto.GenericMessage;
import com.api.personcustomer.service.dto.MovementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
}
