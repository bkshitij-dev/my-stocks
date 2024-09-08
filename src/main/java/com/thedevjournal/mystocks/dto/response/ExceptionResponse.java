package com.thedevjournal.mystocks.dto.response;

import com.thedevjournal.mystocks.enums.ResponseStatus;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ExceptionResponse {

    private ResponseStatus status;
    private int httpCode;
    private String message;
    private List<String> errors;

    public ExceptionResponse(ResponseStatus status, int httpCode, String message, String error) {
        this.status = status;
        this.httpCode = httpCode;
        this.message = message;
        this.errors = Collections.singletonList(error);
    }
}
