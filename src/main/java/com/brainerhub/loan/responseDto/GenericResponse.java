package com.brainerhub.loan.responseDto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public @Data class GenericResponse <T extends Serializable> {
    private boolean success;
    private String message;
    private Object data;
    private HttpStatus status;
}
