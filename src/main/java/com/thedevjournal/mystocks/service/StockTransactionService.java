package com.thedevjournal.mystocks.service;

import com.thedevjournal.mystocks.dto.request.StockTransactionRequestDto;
import com.thedevjournal.mystocks.model.StockTransaction;

import java.util.List;

public interface StockTransactionService {

    void create(StockTransactionRequestDto request);

    List<StockTransaction> list();

    List<StockTransaction> listByScrip(String scrip);

    StockTransaction get(Long id) throws Exception;

    void update(Long id, StockTransactionRequestDto request);
}
