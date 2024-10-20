package com.thedevjournal.mystocks.controller;

import com.thedevjournal.mystocks.constant.AppConstants;
import com.thedevjournal.mystocks.dto.response.ApiResponse;
import com.thedevjournal.mystocks.service.StockHoldingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock-holdings")
@RequiredArgsConstructor
public class StockHoldingController extends BaseController {

    private final StockHoldingService stockHoldingService;

    @GetMapping
    public ResponseEntity<ApiResponse> list() {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, stockHoldingService.list()),
                HttpStatus.OK);
    }
}
