package com.thedevjournal.mystocks.service.impl;

import com.thedevjournal.mystocks.dto.request.StockTransactionRequestDto;
import com.thedevjournal.mystocks.enums.StockType;
import com.thedevjournal.mystocks.enums.TransactionType;
import com.thedevjournal.mystocks.model.Company;
import com.thedevjournal.mystocks.model.StockTransaction;
import com.thedevjournal.mystocks.repository.StockTransactionRepository;
import com.thedevjournal.mystocks.service.CompanyService;
import com.thedevjournal.mystocks.service.StockHoldingService;
import com.thedevjournal.mystocks.service.StockTransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockTransactionServiceImpl implements StockTransactionService {

    private final CompanyService companyService;
    private final StockTransactionRepository stockTransactionRepository;
    private final StockHoldingService stockHoldingService;

    @Override
    @Transactional
    public void create(StockTransactionRequestDto request) {
        Company company = companyService.get(request.getScrip());
        StockTransaction stockTransaction = StockTransaction.builder()
                .company(company)
                .transactionType(TransactionType.valueOf(request.getTransactionType()))
                .stockType(StockType.valueOf(request.getStockType()))
                .quantity(request.getQuantity())
                .rate(request.getRate())
                .build();
        stockTransactionRepository.save(stockTransaction);
        stockHoldingService.updateOnStockTransaction(request.getScrip());
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
        return Optional.of(stockTransactionRepository.findById(id)).get()
                .orElseThrow(() -> new Exception("Cannot find transaction"));
    }

    @Override
    public void update(Long id, StockTransactionRequestDto request) {
        Company company = companyService.get(request.getScrip());
        StockTransaction stockTransaction = StockTransaction.builder()
                .id(id)
                .company(company)
                .transactionType(TransactionType.valueOf(request.getTransactionType()))
                .stockType(StockType.valueOf(request.getStockType()))
                .quantity(request.getQuantity())
                .rate(request.getRate())
                .build();
        stockTransactionRepository.save(stockTransaction);
    }
}
