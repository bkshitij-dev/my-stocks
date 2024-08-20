package com.thedevjournal.mystocks.service;

import com.thedevjournal.mystocks.dto.request.StockMarketHistoryRequestDto;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */
public interface StockMarketHistoryService {

    Long create(StockMarketHistoryRequestDto request);
}
