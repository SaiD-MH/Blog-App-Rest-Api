package com.springboot.blog.exception;

import com.springboot.blog.payload.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<ErrorDto> BlogAppExceptionHandler(
            BlogAppException exception,
            WebRequest webRequest
    ) {

        return new ResponseEntity<>(

                new ErrorDto(exception.getMessage(), new Date(), webRequest.getDescription(false))
                ,
                HttpStatus.BAD_REQUEST
        );

    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> BlogAppExceptionHandler(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ) {

        return new ResponseEntity<>(

                new ErrorDto(exception.getMessage(), new Date(),
                        webRequest.getDescription(false))
                ,
                HttpStatus.NOT_FOUND
        );

    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> validationError = new HashMap<>();

        ex.getAllErrors().forEach(error -> {

            String fieldName = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();
            validationError.put(fieldName, msg);
        });


        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> globalExceptionHandling(
            Exception exception,
            WebRequest webRequest
    ) {

        return new ResponseEntity<>(

                new ErrorDto(exception.getMessage(), new Date(),
                        webRequest.getDescription(false))
                ,
                HttpStatus.INTERNAL_SERVER_ERROR
        );

    }


}
