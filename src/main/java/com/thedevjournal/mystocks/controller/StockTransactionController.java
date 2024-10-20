package com.thedevjournal.mystocks.controller;

import com.thedevjournal.mystocks.constant.AppConstants;
import com.thedevjournal.mystocks.dto.request.StockTransactionRequestDto;
import com.thedevjournal.mystocks.dto.response.ApiResponse;
import com.thedevjournal.mystocks.dto.response.StockTransactionResponseDto;
import com.thedevjournal.mystocks.model.StockTransaction;
import com.thedevjournal.mystocks.service.StockTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-transactions")
@RequiredArgsConstructor
public class StockTransactionController extends BaseController {

    private final StockTransactionService stockTransactionService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody StockTransactionRequestDto request) {
        stockTransactionService.create(request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_SAVE), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> list() {
        List<StockTransactionResponseDto> response = new ArrayList<>();
        List<StockTransaction> stockTransactions = stockTransactionService.list();
        stockTransactions.forEach(stockTransaction -> {
            response.add(prepare(stockTransaction));
        });
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, response), HttpStatus.OK);
    }

    @GetMapping("/stocks/{scrip}")
    public ResponseEntity<ApiResponse> listByScrip(@PathVariable("scrip") String scrip) {
        List<StockTransactionResponseDto> response = new ArrayList<>();
        List<StockTransaction> stockTransactions = stockTransactionService.listByScrip(scrip);
        stockTransactions.forEach(stockTransaction -> {
            response.add(prepare(stockTransaction));
        });
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable("id") Long id) throws Exception {
        StockTransaction stockTransaction = stockTransactionService.get(id);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, prepare(stockTransaction)),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id, @RequestBody StockTransactionRequestDto request) {
        stockTransactionService.update(id, request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE), HttpStatus.OK);
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
