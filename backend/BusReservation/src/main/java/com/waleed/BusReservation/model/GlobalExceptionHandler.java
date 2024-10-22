package com.waleed.BusReservation.model;

import java.nio.file.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservationApiException.class)
    public ResponseEntity<ErrorDetails> handleReservationApiException(ReservationApiException exception, WebRequest webRequest) {
        final ErrorDetails errorDetails = new ErrorDetails();   
        errorDetails.setErrorCode(exception.getHttpStatus().value());
        errorDetails.setErrorMessage(exception.getLocalizedMessage());
        errorDetails.setDeveloperErrorMessage(webRequest.getDescription(false));
        errorDetails.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }  

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException exception, WebRequest webRequest) {
        final ErrorDetails errorDetails = new ErrorDetails();   
        errorDetails.setErrorCode(HttpStatus.UNAUTHORIZED.value());
        errorDetails.setErrorMessage(exception.getLocalizedMessage());
        errorDetails.setDeveloperErrorMessage(webRequest.getDescription(false));
        errorDetails.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest) {
        final ErrorDetails errorDetails = new ErrorDetails();   
        errorDetails.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setErrorMessage(exception.getLocalizedMessage());
        errorDetails.setDeveloperErrorMessage(webRequest.getDescription(false));
        errorDetails.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }    
}
