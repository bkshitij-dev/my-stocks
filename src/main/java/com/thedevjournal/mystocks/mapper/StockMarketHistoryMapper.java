package com.thedevjournal.mystocks.mapper;

/*
 * @author Kshitij
 * @created 20-Aug-2024
 */

import com.thedevjournal.mystocks.dto.response.RecentMarketDataResponseDto;
import com.thedevjournal.mystocks.dto.response.StockMarketHistoryResponseDto;
import com.thedevjournal.mystocks.model.StockMarketHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockMarketHistoryMapper {

    Long updateLiveData(@Param("request") StockMarketHistory request);

    StockMarketHistoryResponseDto getCurrentData();

    List<RecentMarketDataResponseDto> getRecentData();
}
