package com.thedevjournal.mystocks.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTransactionRequestDto {

    private Long companyId;
    private String transactionType;
    private String stockType;
    private Integer quantity;
    private BigDecimal rate;
}
