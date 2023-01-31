package br.com.adrianomenezes.helpdesk.resources.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.adrianomenezes.helpdesk.services.exceptions.ObjectnotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(ObjectnotFoundException.class)
    public ResponseEntity<StandardError> objectnotFoundException(
        ObjectnotFoundException ex, HttpServletRequest request
    ) {
        StandardError error = new StandardError(
            System.currentTimeMillis(),
            HttpStatus.NOT_FOUND.value(),
            "Object not found",
            ex.getMessage(),
            request.getRequestURI()
                    );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        
    }
}
