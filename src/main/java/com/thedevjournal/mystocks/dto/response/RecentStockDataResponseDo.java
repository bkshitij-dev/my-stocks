package com.thedevjournal.mystocks.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecentStockDataResponseDo {

    private String name;
    private String scrip;

    @Builder.Default
    private List<RecentMarketDataResponseDto> recentData = new ArrayList<>();
}
