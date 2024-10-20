package com.thedevjournal.mystocks.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockRSIParamsResponseDto {

    private LocalDate date;
    private BigDecimal pointsChange;

}
