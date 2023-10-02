package com.api.movementsaccount.exception;

import com.api.movementsaccount.service.dto.GenericMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerValidateException(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(e ->{
            errors.put(((FieldError)e).getField(),e.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        return new ResponseEntity<>(new GenericMessage(HttpStatus.EXPECTATION_FAILED.toString(),exception.getMessage()), HttpStatus.EXPECTATION_FAILED);
    }
    @ExceptionHandler(AtAlreadyExistsException.class)
    public ResponseEntity<?> handleAtAlreadyExistsException(AtAlreadyExistsException exception, WebRequest request){
        return new ResponseEntity<>(new GenericMessage(HttpStatus.EXPECTATION_FAILED.toString(),exception.getMessage()), HttpStatus.EXPECTATION_FAILED);
    }
    @ExceptionHandler(MovementException.class)
    public ResponseEntity<?> handleMovementException(MovementException exception, WebRequest request){
        return new ResponseEntity<>(new GenericMessage(HttpStatus.EXPECTATION_FAILED.toString(),exception.getMessage()), HttpStatus.EXPECTATION_FAILED);
    }
}
