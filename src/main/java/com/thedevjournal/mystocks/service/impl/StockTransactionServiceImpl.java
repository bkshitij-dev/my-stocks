package com.thedevjournal.mystocks.service.impl;

import com.thedevjournal.mystocks.dto.request.StockTransactionRequestDto;
import com.thedevjournal.mystocks.enums.StockType;
import com.thedevjournal.mystocks.enums.TransactionType;
import com.thedevjournal.mystocks.model.Company;
import com.thedevjournal.mystocks.model.StockTransaction;
import com.thedevjournal.mystocks.repository.StockTransactionRepository;
import com.thedevjournal.mystocks.service.StockHoldingService;
import com.thedevjournal.mystocks.service.StockTransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockTransactionServiceImpl implements StockTransactionService {

    private final StockTransactionRepository stockTransactionRepository;
    private final StockHoldingService stockHoldingService;

    @Override
    @Transactional
    public void create(StockTransactionRequestDto request) {
        StockTransaction stockTransaction = StockTransaction.builder()
                .company(Company.builder().id(request.getCompanyId()).build())
                .transactionType(TransactionType.valueOf(request.getTransactionType()))
                .stockType(StockType.valueOf(request.getStockType()))
                .quantity(request.getQuantity())
                .rate(request.getRate())
                .build();
        stockTransactionRepository.save(stockTransaction);
        stockHoldingService.updateOnStockTransaction(request.getCompanyId());
    }

    @Override
    public List<StockTransaction> list() {
        return stockTransactionRepository.findAll();
    }

    @Override
    public List<StockTransaction> listByScrip(String scrip) {
        return stockTransactionRepository.findAllByScrip(scrip);
    }

    @Override
    public StockTransaction get(Long id) throws Exception {
        return stockTransactionRepository.findById(id)
                .orElseThrow(() -> new Exception("Cannot find transaction"));
    }

    @Override
    public void update(Long id, StockTransactionRequestDto request) {
        StockTransaction stockTransaction = StockTransaction.builder()
                .id(id)
                .company(Company.builder().id(request.getCompanyId()).build())
                .transactionType(TransactionType.valueOf(request.getTransactionType()))
                .stockType(StockType.valueOf(request.getStockType()))
                .quantity(request.getQuantity())
                .rate(request.getRate())
                .build();
        stockTransactionRepository.save(stockTransaction);
    }
}
