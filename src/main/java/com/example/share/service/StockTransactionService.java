package com.example.share.service;

import com.example.share.dto.request.StockTransactionRequestDto;
import com.example.share.dto.response.ScriptDetailResponseDto;
import com.example.share.model.StockTransaction;

import java.util.List;

public interface StockTransactionService {

    void create(StockTransactionRequestDto request);

    List<StockTransaction> list();

    List<ScriptDetailResponseDto> groupByScrip();

    List<StockTransaction> listByScrip(String scrip);

    StockTransaction get(Long id) throws Exception;

    void update(Long id, StockTransactionRequestDto request);
}
