package com.sticast.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger logger = Logger.getLogger(getClass().getName());

    //Since spring has handleMethodArgumentNotValid() by default, we need to override the behaviour
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logger.warning(errors.toString());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            WebRequest request)
    {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), error);

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({DisabledException.class})
    public ResponseEntity<Object> handleUserDisabled(
            DisabledException ex,
            WebRequest request)
    {
        String error = "This account is disabled!";

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), error);

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentials(
            BadCredentialsException ex,
            WebRequest request)
    {
        String error = "Bad credentials!";

        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), error);

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }


}