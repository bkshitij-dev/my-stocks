package com.thedevjournal.mystocks.controller;

import com.thedevjournal.mystocks.dto.response.ApiResponse;
import com.thedevjournal.mystocks.enums.ResponseStatus;

public class BaseController {

    protected ApiResponse successResponse(String message, Object data) {
        return ApiResponse.builder()
                .status(ResponseStatus.SUCCESS)
                .message(message)
                .data(data)
                .build();
    }

    protected ApiResponse successResponse(String message) {
        return successResponse(message, null);
    }

    protected ApiResponse errorResponse(String message, Object data) {
        return ApiResponse.builder()
                .status(ResponseStatus.ERROR)
                .message(message)
                .data(data)
                .build();
    }
}
