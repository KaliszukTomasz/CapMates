package com.example.game.controller;

import com.example.game.exceptions.UserNotFondException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sun.awt.SunHints;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> noSuchElement(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "No such element in database, please chanege filter";
        return ResponseEntity.status(HttpStatus.valueOf(403)).body(bodyOfResponse);

    }

    @ExceptionHandler(value = UserNotFondException.class)
    public ResponseEntity<Object> userNotFoundException(RuntimeException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.valueOf(404)).body("Processing error: " + ex.getMessage());

    }

}

