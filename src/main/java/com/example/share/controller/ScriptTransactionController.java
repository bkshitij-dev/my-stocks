package com.example.share.controller;

import com.example.share.dto.request.ScriptTransactionRequestDto;
import com.example.share.dto.response.ScriptDetailResponseDto;
import com.example.share.dto.response.ScriptTransactionResponseDto;
import com.example.share.model.ScriptTransaction;
import com.example.share.service.ScriptTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/script-transactions")
@RequiredArgsConstructor
public class ScriptTransactionController {

    private final ScriptTransactionService scriptTransactionService;

    @PostMapping
    public String create(@RequestBody ScriptTransactionRequestDto request) {
        scriptTransactionService.create(request);
        return "SUCCESS";
    }

    @GetMapping
    public List<ScriptTransactionResponseDto> list() {
        List<ScriptTransactionResponseDto> response = new ArrayList<>();
        List<ScriptTransaction> scriptTransactions = scriptTransactionService.list();
        scriptTransactions.forEach(scriptTransaction -> {
            response.add(prepare(scriptTransaction));
        });
        return response;
    }

    @GetMapping("/scripts")
    public List<ScriptDetailResponseDto> groupByScript() {
        return scriptTransactionService.groupByScript();
    }

    @GetMapping("/scripts/{symbol}")
    public List<ScriptTransactionResponseDto> listByScript(@PathVariable("symbol") String symbol) {
        List<ScriptTransactionResponseDto> response = new ArrayList<>();
        List<ScriptTransaction> scriptTransactions = scriptTransactionService.listByScript(symbol);
        scriptTransactions.forEach(scriptTransaction -> {
            response.add(prepare(scriptTransaction));
        });
        return response;
    }

    @GetMapping("/{id}")
    public ScriptTransactionResponseDto get(@PathVariable("id") Long id) throws Exception {
        ScriptTransaction scriptTransaction = scriptTransactionService.get(id);
        return prepare(scriptTransaction);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody ScriptTransactionRequestDto request) {
        scriptTransactionService.update(id, request);
        return "SUCCESS";
    }

    private ScriptTransactionResponseDto prepare(ScriptTransaction scriptTransaction) {
        return ScriptTransactionResponseDto.builder()
                .id(scriptTransaction.getId())
                .scriptSymbol(scriptTransaction.getScript().getSymbol())
                .transactionType(scriptTransaction.getTransactionType().name())
                .shareType(scriptTransaction.getShareType().name())
                .quantity(scriptTransaction.getQuantity())
                .rate(scriptTransaction.getRate())
                .amount(BigDecimal.valueOf(scriptTransaction.getQuantity()).multiply(scriptTransaction.getRate()))
                .build();
    }
}
