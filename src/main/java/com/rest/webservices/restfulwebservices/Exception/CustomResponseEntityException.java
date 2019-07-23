package com.rest.webservices.restfulwebservices.Exception;

import com.rest.webservices.restfulwebservices.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
@RestController
public class CustomResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
       ExceptionResponse expenseResponse= new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));

       return new ResponseEntity(expenseResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
     @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse expenseResponse= new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(expenseResponse, HttpStatus.NOT_FOUND);

    }
@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ExceptionResponse expenseResponse = new ExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());

    return new ResponseEntity(expenseResponse, HttpStatus.BAD_REQUEST);
}
}

