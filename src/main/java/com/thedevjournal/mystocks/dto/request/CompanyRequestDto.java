package com.thedevjournal.mystocks.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDto {

    private String name;
    private String scrip;
    private String sector;
    private BigDecimal ltp;
}
