package com.thedevjournal.mystocks.service;

import com.thedevjournal.mystocks.dto.response.RecentStockDataResponseDo;
import com.thedevjournal.mystocks.dto.response.StockMFIResponseDto;
import com.thedevjournal.mystocks.dto.response.StockRSIResponseDto;

import java.util.List;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */
public interface StockHistoryService {

    void fetchLiveData() throws Exception;

    RecentStockDataResponseDo getRecentData(String scrip);

    List<StockMFIResponseDto> getMFI();

    List<StockRSIResponseDto> getRSI();
}
