package com.thedevjournal.mystocks.dto.request;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMarketHistoryRequestDto {

    private String date;
    private String time;
    private BigDecimal index;
    private BigDecimal percentageChange;
}
