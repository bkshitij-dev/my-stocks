package com.thedevjournal.mystocks.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockHoldingResponseDto {

    private Long id;
    private String scrip;
    private Integer totalBuyQuantity;
    private Integer totalSellQuantity;
    private BigDecimal totalBuyAmount;
    private BigDecimal totalSellAmount;
    private Integer holdingQuantity;
    private BigDecimal currentInvestment;
    private BigDecimal wacc;
}
