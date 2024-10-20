package com.thedevjournal.mystocks.mapper;

/*
 * @author Kshitij
 * @created 20-Aug-2024
 */

import com.thedevjournal.mystocks.dto.request.StockHistoryRequestDto;
import com.thedevjournal.mystocks.dto.response.RecentStockDataResponseDo;
import com.thedevjournal.mystocks.dto.response.StockMFIParamsResponseDto;
import com.thedevjournal.mystocks.dto.response.StockRSIParamsResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockHistoryMapper {

    void updateLiveData(@Param("request") StockHistoryRequestDto request, @Param("companyId") Long companyId);

    RecentStockDataResponseDo getRecentData(@Param("scrip") String scrip);

    List<StockMFIParamsResponseDto> getMFI(@Param("scrip") String scrip);

    List<StockRSIParamsResponseDto> getRSI(@Param("scrip") String scrip);
}
