package com.example.share.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScriptDetailResponseDto {

    private String symbol;
    private Integer totalBuyQuantity;
    private Integer totalSellQuantity;
    private BigDecimal totalBuy;
    private BigDecimal totalSell;
    private Integer currentQuantity;
    private BigDecimal currentInvestment;
    private BigDecimal pricePerShare;
}
