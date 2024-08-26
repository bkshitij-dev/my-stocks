package com.thedevjournal.mystocks.controller;

/*
 * @author Kshitij
 * @created 24-Aug-2024
 */

import com.thedevjournal.mystocks.dto.response.StockMarketHistoryResponseDto;
import com.thedevjournal.mystocks.service.StockMarketHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock-market-history")
@RequiredArgsConstructor
public class StockMarketHistoryController {

    private final StockMarketHistoryService stockMarketHistoryService;

    @GetMapping("/current-data")
    public StockMarketHistoryResponseDto getCurrentData() {
        return stockMarketHistoryService.getCurrentData();
    }
}
