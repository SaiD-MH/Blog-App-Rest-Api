package com.springboot.blog.exception;

import com.springboot.blog.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<ErrorDto> BlogAppExceptionHandler(
            BlogAppException exception ,
            WebRequest webRequest
    ){

        return new ResponseEntity<>(

                new ErrorDto(exception.getMessage() , new Date() , webRequest.getDescription(false))
                ,
                HttpStatus.BAD_REQUEST
        );

    }



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> BlogAppExceptionHandler(
            ResourceNotFoundException exception ,
            WebRequest webRequest
    ){

        return new ResponseEntity<>(

                new ErrorDto(exception.getMessage() , new Date() ,
                        webRequest.getDescription(false))
                ,
                HttpStatus.NOT_FOUND
        );

    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> globalExceptionHandling(
            Exception exception ,
            WebRequest webRequest
    ){

        return new ResponseEntity<>(

                new ErrorDto(exception.getMessage() , new Date() ,
                        webRequest.getDescription(false))
                ,
                HttpStatus.INTERNAL_SERVER_ERROR
        );

    }


}
