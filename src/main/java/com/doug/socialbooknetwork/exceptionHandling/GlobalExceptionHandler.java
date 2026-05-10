package com.doug.socialbooknetwork.exceptionHandling;

import com.doug.socialbooknetwork.payload.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException exp){
        final var status = HttpStatus.CONFLICT.value();
        final var message = e.getMessage();
        return ResponseEntity.status(status).body(generatedApiResponse(status,message));
    }




    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleException(UserAlreadyExistsException e){
        final var status = HttpStatus.CONFLICT.value();
        final var message = e.getMessage();
        return ResponseEntity.status(status).body(generatedApiResponse(status,message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        final var status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        final var message = e.getMessage();
        return ResponseEntity.status(status).body(generatedApiResponse(status,message));
    }

    private static ApiResponse generatedApiResponse(int status, String message) {
        return new ApiResponse(
                status,message, Instant.now().toString()
        );
    }
}
