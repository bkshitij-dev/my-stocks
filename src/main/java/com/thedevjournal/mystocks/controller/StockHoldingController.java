package com.thedevjournal.mystocks.controller;

import com.thedevjournal.mystocks.dto.response.StockHoldingResponseDto;
import com.thedevjournal.mystocks.service.StockHoldingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-holdings")
@RequiredArgsConstructor
public class StockHoldingController {

    private final StockHoldingService stockHoldingService;

    @GetMapping
    public List<StockHoldingResponseDto> list() {
        return stockHoldingService.list();
    }
}
