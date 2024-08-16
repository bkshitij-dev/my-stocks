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
public class StockTransactionResponseDto {

    private Long id;
    private String scrip;
    private String transactionType;
    private String stockType;
    private Integer quantity;
    private BigDecimal rate;
    private BigDecimal amount;
}
