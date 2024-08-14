package com.example.share.service.impl;

import com.example.share.dto.request.ScriptTransactionRequestDto;
import com.example.share.dto.response.ScriptDetailResponseDto;
import com.example.share.enums.ShareType;
import com.example.share.enums.TransactionType;
import com.example.share.mapper.ScriptDetailMapper;
import com.example.share.model.Script;
import com.example.share.model.ScriptTransaction;
import com.example.share.repository.ScriptTransactionRepository;
import com.example.share.service.ScriptService;
import com.example.share.service.ScriptTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScriptTransactionServiceImpl implements ScriptTransactionService {

    private final ScriptService scriptService;
    private final ScriptTransactionRepository scriptTransactionRepository;
    private final ScriptDetailMapper scriptDetailMapper;

    @Override
    public void create(ScriptTransactionRequestDto request) {
        Script script = scriptService.get(request.getScriptSymbol());
        ScriptTransaction scriptTransaction = ScriptTransaction.builder()
                .script(script)
                .transactionType(TransactionType.valueOf(request.getTransactionType()))
                .shareType(ShareType.valueOf(request.getShareType()))
                .quantity(request.getQuantity())
                .rate(request.getRate())
                .build();
        scriptTransactionRepository.save(scriptTransaction);
    }

    @Override
    public List<ScriptTransaction> list() {
        return scriptTransactionRepository.findAll();
    }

    @Override
    public List<ScriptDetailResponseDto> groupByScript(){
        return scriptDetailMapper.groupByScript();
    }

    @Override
    public List<ScriptTransaction> listByScript(String symbol) {
        return scriptTransactionRepository.findAllByScript(symbol);
    }

    @Override
    public ScriptTransaction get(Long id) throws Exception {
        return Optional.of(scriptTransactionRepository.findById(id)).get()
                .orElseThrow(() -> new Exception("Cannot find transaction"));
    }

    @Override
    public void update(Long id, ScriptTransactionRequestDto request) {
        Script script = scriptService.get(request.getScriptSymbol());
        ScriptTransaction scriptTransaction = ScriptTransaction.builder()
                .id(id)
                .script(script)
                .transactionType(TransactionType.valueOf(request.getTransactionType()))
                .shareType(ShareType.valueOf(request.getShareType()))
                .quantity(request.getQuantity())
                .rate(request.getRate())
                .build();
        scriptTransactionRepository.save(scriptTransaction);
    }
}
