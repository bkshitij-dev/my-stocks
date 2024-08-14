package com.example.share.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptRequestDto {

    private String name;
    private String symbol;
    private String sector;
    private BigDecimal ltp;
}
