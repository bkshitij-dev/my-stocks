package com.thedevjournal.mystocks.service;

import com.thedevjournal.mystocks.dto.request.StockMarketHistoryRequestDto;
import com.thedevjournal.mystocks.dto.response.StockMarketHistoryResponseDto;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */
public interface StockMarketHistoryService {

    Long create(StockMarketHistoryRequestDto request);

    StockMarketHistoryResponseDto getCurrentData();
}
