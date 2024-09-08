package com.thedevjournal.mystocks.dto.response;

import com.thedevjournal.mystocks.enums.ResponseStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {

    private String message;
    private Object data;
    private ResponseStatus status;
}
