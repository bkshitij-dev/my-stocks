package com.thedevjournal.mystocks.service;

import com.thedevjournal.mystocks.dto.response.RecentStockDataResponseDo;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */
public interface StockHistoryService {

    void fetchLiveData() throws Exception;

    RecentStockDataResponseDo getRecentData(String scrip);
}
