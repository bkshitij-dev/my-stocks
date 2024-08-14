package com.example.share.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptTransactionRequestDto {

    private String scriptSymbol;
    private String transactionType;
    private String shareType;
    private Integer quantity;
    private BigDecimal rate;
}
