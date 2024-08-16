package com.thedevjournal.mystocks.controller;

import com.thedevjournal.mystocks.dto.request.StockTransactionRequestDto;
import com.thedevjournal.mystocks.dto.response.StockTransactionResponseDto;
import com.thedevjournal.mystocks.model.StockTransaction;
import com.thedevjournal.mystocks.service.StockTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-transactions")
@RequiredArgsConstructor
public class StockTransactionController {

    private final StockTransactionService stockTransactionService;

    @PostMapping
    public String create(@RequestBody StockTransactionRequestDto request) {
        stockTransactionService.create(request);
        return "SUCCESS";
    }

    @GetMapping
    public List<StockTransactionResponseDto> list() {
        List<StockTransactionResponseDto> response = new ArrayList<>();
        List<StockTransaction> stockTransactions = stockTransactionService.list();
        stockTransactions.forEach(stockTransaction -> {
            response.add(prepare(stockTransaction));
        });
        return response;
    }

    @GetMapping("/stocks/{scrip}")
    public List<StockTransactionResponseDto> listByScrip(@PathVariable("scrip") String scrip) {
        List<StockTransactionResponseDto> response = new ArrayList<>();
        List<StockTransaction> stockTransactions = stockTransactionService.listByScrip(scrip);
        stockTransactions.forEach(stockTransaction -> {
            response.add(prepare(stockTransaction));
        });
        return response;
    }

    @GetMapping("/{id}")
    public StockTransactionResponseDto get(@PathVariable("id") Long id) throws Exception {
        StockTransaction stockTransaction = stockTransactionService.get(id);
        return prepare(stockTransaction);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody StockTransactionRequestDto request) {
        stockTransactionService.update(id, request);
        return "SUCCESS";
    }

    private StockTransactionResponseDto prepare(StockTransaction stockTransaction) {
        return StockTransactionResponseDto.builder()
                .id(stockTransaction.getId())
                .scrip(stockTransaction.getCompany().getScrip())
                .transactionType(stockTransaction.getTransactionType().name())
                .stockType(stockTransaction.getStockType().name())
                .quantity(stockTransaction.getQuantity())
                .rate(stockTransaction.getRate())
                .amount(BigDecimal.valueOf(stockTransaction.getQuantity()).multiply(stockTransaction.getRate()))
                .build();
    }
}
