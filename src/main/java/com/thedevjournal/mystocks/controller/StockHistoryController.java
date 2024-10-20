package com.thedevjournal.mystocks.controller;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

import com.thedevjournal.mystocks.constant.AppConstants;
import com.thedevjournal.mystocks.dto.response.ApiResponse;
import com.thedevjournal.mystocks.service.StockHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock-history")
@RequiredArgsConstructor
public class StockHistoryController extends BaseController {

    private final StockHistoryService stockHistoryService;

    @GetMapping("/live-data")
    public ResponseEntity<ApiResponse> getLiveData() throws Exception {
        stockHistoryService.fetchLiveData();
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE), HttpStatus.OK);
    }

    @GetMapping("/scrip/{scrip}")
    public ResponseEntity<ApiResponse> getRecentData(@PathVariable("scrip") String scrip) throws Exception {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE,
                stockHistoryService.getRecentData(scrip)), HttpStatus.OK);
    }

    @GetMapping("/mfi")
    public ResponseEntity<ApiResponse> getMFI() {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE,
                stockHistoryService.getMFI()), HttpStatus.OK);
    }

    @GetMapping("/rsi")
    public ResponseEntity<ApiResponse> getRSI() {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE,
                stockHistoryService.getRSI()), HttpStatus.OK);
    }
}
