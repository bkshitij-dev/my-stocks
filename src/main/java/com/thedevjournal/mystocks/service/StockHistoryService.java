package com.thedevjournal.mystocks.service;

import com.thedevjournal.mystocks.dto.response.RecentMarketDataResponseDto;

import java.util.List;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */
public interface StockHistoryService {

    void fetchLiveData() throws Exception;

    List<RecentMarketDataResponseDto> getRecentData(String scrip);
}
