package com.thedevjournal.mystocks.service;

import com.thedevjournal.mystocks.dto.response.StockHoldingResponseDto;

import java.util.List;

public interface StockHoldingService {

    void updateOnStockTransaction(Long companyId);

    List<StockHoldingResponseDto> list();
}
