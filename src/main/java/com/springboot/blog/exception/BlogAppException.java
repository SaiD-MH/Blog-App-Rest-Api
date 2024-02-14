package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogAppException extends RuntimeException{

    HttpStatus status;
    String msg;

    public BlogAppException(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public BlogAppException(String message, HttpStatus status, String msg) {
        super(message);
        this.status = status;
        this.msg = msg;
    }

    public BlogAppException(String message, Throwable cause, HttpStatus status, String msg) {
        super(message, cause);
        this.status = status;
        this.msg = msg;
    }

    public BlogAppException(Throwable cause, HttpStatus status, String msg) {
        super(cause);
        this.status = status;
        this.msg = msg;
    }

    public BlogAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
        this.msg = msg;
    }
}
