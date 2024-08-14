package com.example.share.service.impl;

import com.example.share.dto.request.ScriptRequestDto;
import com.example.share.dto.response.ScriptResponseDto;
import com.example.share.enums.Sector;
import com.example.share.model.Script;
import com.example.share.repository.ScriptRepository;
import com.example.share.service.ScriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;

    @Override
    public void create(ScriptRequestDto request) {
        Script script = Script.builder()
                .name(request.getName())
                .symbol(request.getSymbol())
                .sector(Sector.valueOf(request.getSector()))
                .ltp(request.getLtp())
                .build();
        scriptRepository.save(script);
    }

    @Override
    public List<Script> list() {
        return scriptRepository.findAll();
    }

    @Override
    public Script get(String symbol) {
        return scriptRepository.findBySymbol(symbol);
    }

    @Override
    public void update(Long id, ScriptRequestDto request) {
        Script script = Script.builder()
                .id(id)
                .name(request.getName())
                .symbol(request.getSymbol())
                .sector(Sector.valueOf(request.getSector()))
                .ltp(request.getLtp())
                .build();
        scriptRepository.save(script);
    }
}
