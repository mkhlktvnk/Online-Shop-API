package edu.bsuir.onlineshop.web.advice;

import edu.bsuir.onlineshop.service.exception.ResourceAlreadyPresentException;
import edu.bsuir.onlineshop.service.exception.ResourceNotFoundException;
import edu.bsuir.onlineshop.web.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException e) {
        ApiError apiError = ApiError.builder()
                .details(List.of(e.getMessage()))
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceAlreadyPresentException.class)
    public ResponseEntity<ApiError> handleResourceAlreadyPresentException(ResourceAlreadyPresentException e) {
        ApiError apiError = ApiError.builder()
                .details(List.of(e.getMessage()))
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

}
