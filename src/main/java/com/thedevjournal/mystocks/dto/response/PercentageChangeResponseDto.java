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
public class PercentageChangeResponseDto {

    private String scrip;
    private BigDecimal totalPercentageChange;
    private BigDecimal latestPercentageChange;
}
