package com.example.share.service;

import com.example.share.dto.request.ScriptTransactionRequestDto;
import com.example.share.dto.response.ScriptDetailResponseDto;
import com.example.share.model.ScriptTransaction;

import java.util.List;

public interface ScriptTransactionService {

    void create(ScriptTransactionRequestDto request);

    List<ScriptTransaction> list();

    List<ScriptDetailResponseDto> groupByScript();

    List<ScriptTransaction> listByScript(String symbol);

    ScriptTransaction get(Long id) throws Exception;

    void update(Long id, ScriptTransactionRequestDto request);
}
