package com.shvaindahder.testtask.exceptions;


import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NotFoundControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            StudentNotFoundException.class,
            StudentsGroupNotFoundException.class,
            SubjectNotFoundException.class
    }) public ResponseEntity<Response> handleConflict(Exception ex) {
        Response response = new Response();
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
