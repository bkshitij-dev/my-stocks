package com.thedevjournal.mystocks.controller;

/*
 * @author Kshitij
 * @created 24-Aug-2024
 */

import com.thedevjournal.mystocks.constant.AppConstants;
import com.thedevjournal.mystocks.dto.response.ApiResponse;
import com.thedevjournal.mystocks.service.StockMarketHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock-market-history")
@RequiredArgsConstructor
public class StockMarketHistoryController extends BaseController {

    private final StockMarketHistoryService stockMarketHistoryService;

    @GetMapping("/current-data")
    public ResponseEntity<ApiResponse> getCurrentData() {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE,
                stockMarketHistoryService.getCurrentData()), HttpStatus.OK);
    }
}
