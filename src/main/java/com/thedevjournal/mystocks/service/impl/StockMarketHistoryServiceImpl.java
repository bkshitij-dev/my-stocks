package com.thedevjournal.mystocks.service.impl;

import com.thedevjournal.mystocks.dto.request.StockMarketHistoryRequestDto;
import com.thedevjournal.mystocks.dto.response.PercentageChangeResponseDto;
import com.thedevjournal.mystocks.dto.response.RecentMarketDataResponseDto;
import com.thedevjournal.mystocks.dto.response.StockMarketHistoryResponseDto;
import com.thedevjournal.mystocks.mapper.StockMarketHistoryMapper;
import com.thedevjournal.mystocks.model.StockMarketHistory;
import com.thedevjournal.mystocks.service.StockMarketHistoryService;
import com.thedevjournal.mystocks.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

@Service
@RequiredArgsConstructor
public class StockMarketHistoryServiceImpl implements StockMarketHistoryService {

    private final StockMarketHistoryMapper stockMarketHistoryMapper;

    @Override
    public Long create(StockMarketHistoryRequestDto request) {
        StockMarketHistory stockMarketHistory = StockMarketHistory.builder()
                .date(DateUtil.getDate(request.getDate()))
                .time(DateUtil.getTime(request.getTime()))
                .index(request.getIndex())
                .percentageChange(request.getPercentageChange())
                .build();
        return stockMarketHistoryMapper.updateLiveData(stockMarketHistory);
    }

    @Override
    public StockMarketHistoryResponseDto getCurrentData() {
        return stockMarketHistoryMapper.getCurrentData();
    }

    @Override
    public List<RecentMarketDataResponseDto> getRecentData() {
        List<RecentMarketDataResponseDto> recentData = stockMarketHistoryMapper.getRecentData();
        Collections.reverse(recentData);
        return recentData;
    }

    @Override
    public List<PercentageChangeResponseDto> getTopGainers(int days) {
        List<PercentageChangeResponseDto> result = stockMarketHistoryMapper.getTopChanges(days, true);
        result.sort(Comparator.comparing(PercentageChangeResponseDto::getTotalPercentageChange).reversed());
        return result;
    }

    @Override
    public List<PercentageChangeResponseDto> getTopLosers(int days) {
        return stockMarketHistoryMapper.getTopChanges(days, false);
    }
}
