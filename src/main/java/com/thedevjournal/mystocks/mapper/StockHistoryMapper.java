package com.thedevjournal.mystocks.mapper;

/*
 * @author Kshitij
 * @created 20-Aug-2024
 */

import com.thedevjournal.mystocks.dto.request.StockHistoryRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StockHistoryMapper {

    void updateLiveData(@Param("request") StockHistoryRequestDto request, @Param("companyId") Long companyId);
}
