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
public class CompanyResponseDto {

    private Long id;
    private String name;
    private String scrip;
    private String sector;
    private BigDecimal ltp;
}
