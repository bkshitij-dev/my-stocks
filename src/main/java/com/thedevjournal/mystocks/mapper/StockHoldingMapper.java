package com.thedevjournal.mystocks.mapper;

import com.thedevjournal.mystocks.dto.response.StockHoldingResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockHoldingMapper {

    List<StockHoldingResponseDto> list();
}
