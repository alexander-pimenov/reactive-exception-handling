package com.javatechie.reactive.annotationapproach;

import com.javatechie.reactive.exception.BookAPIException;
import com.javatechie.reactive.exception.MalformedJSONException;
import com.javatechie.reactive.exception.ThreadInterruptedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс глобальной классической обработки исключений.
 * Как и при использовании RestTemplate.
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler(BookAPIException.class)
    public ResponseEntity<?> handleBookAPIException(BookAPIException bookAPIException) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error message", bookAPIException.getMessage());
        errorMap.put("status", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler({Exception.class, ThreadInterruptedException.class})
    protected ResponseEntity<Object> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error. Exception message: " + ex.getMessage());
    }

    @ExceptionHandler(MalformedJSONException.class)
    protected ResponseEntity<Object> handleJSONException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JSON processing exception. Exception message: " + ex.getMessage());
    }
}


