package com.sticast.exceptions;

import lombok.Data;
import java.util.Arrays;
import java.util.List;

@Data
public class ApiError {
    private Integer status;
    private String message;
    private List<String> errors;

    public ApiError(Integer status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(Integer status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
    }
}