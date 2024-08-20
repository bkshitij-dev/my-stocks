package com.thedevjournal.mystocks.service.impl;

import com.thedevjournal.mystocks.dto.response.StockHoldingResponseDto;
import com.thedevjournal.mystocks.mapper.StockHoldingMapper;
import com.thedevjournal.mystocks.repository.StockHoldingRepository;
import com.thedevjournal.mystocks.service.StockHoldingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockHoldingServiceImpl implements StockHoldingService {

    private final StockHoldingRepository stockHoldingRepository;
    private final StockHoldingMapper stockHoldingMapper;

    @Override
    public void updateOnStockTransaction(Long companyId) {
        stockHoldingRepository.updateOnStockTransaction(companyId);
    }

    @Override
    public List<StockHoldingResponseDto> list() {
        return stockHoldingMapper.list();
    }
}
