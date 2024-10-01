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
public class StockHistoryRequestDto {

    private String scrip;
    private BigDecimal ltp;
    private BigDecimal pointsChange;
    private BigDecimal percentageChange;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal volume;
    private Long stockMarketHistoryId;
}
