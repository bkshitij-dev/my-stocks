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
public class ScriptTransactionResponseDto {

    private Long id;
    private String scriptSymbol;
    private String transactionType;
    private String shareType;
    private Integer quantity;
    private BigDecimal rate;
    private BigDecimal amount;
}
