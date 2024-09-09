package com.thedevjournal.mystocks.dto.response;

/*
 * @author Kshitij
 * @created 24-Aug-2024
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMarketHistoryResponseDto {

    private String date;
    private String time;
    private BigDecimal index;
    private BigDecimal pointsChange;
    private BigDecimal percentageChange;
    private List<StockHistoryResponseDto> stocks;
}
