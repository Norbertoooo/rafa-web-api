package com.rafa.web.api.web.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseException> notFoundException(NotFoundException ex, WebRequest request) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(new ResponseException(ex.getMessage(), NOT_FOUND.value(), getUrl(request)));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> internalServerErrorException(Exception ex, WebRequest request) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(new ResponseException(ex.getMessage(), INTERNAL_SERVER_ERROR.value(), getUrl(request)));
    }

    // TODO: 20/03/2021 não está lançando exceção personalizada
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseException> forbiddenException(Exception ex, WebRequest request) {
        return ResponseEntity
                .status(FORBIDDEN)
                .body(new ResponseException(ex.getMessage(), FORBIDDEN.value(), getUrl(request)));
    }

    public String getUrl(WebRequest webRequest) {
        return webRequest.getDescription(false).substring(4);
    }
}


