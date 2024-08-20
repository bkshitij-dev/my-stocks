package com.thedevjournal.mystocks.service.impl;

import com.thedevjournal.mystocks.dto.request.StockMarketHistoryRequestDto;
import com.thedevjournal.mystocks.mapper.StockMarketHistoryMapper;
import com.thedevjournal.mystocks.model.StockMarketHistory;
import com.thedevjournal.mystocks.repository.StockMarketHistoryRepository;
import com.thedevjournal.mystocks.service.StockMarketHistoryService;
import com.thedevjournal.mystocks.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

@Service
@RequiredArgsConstructor
public class StockMarketHistoryServiceImpl implements StockMarketHistoryService {

    private final StockMarketHistoryRepository stockMarketHistoryRepository;
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
}
