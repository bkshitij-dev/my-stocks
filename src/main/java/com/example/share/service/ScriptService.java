package com.example.share.service;

import com.example.share.dto.request.ScriptRequestDto;
import com.example.share.model.Script;

import java.util.List;

public interface ScriptService {

    void create(ScriptRequestDto request);

    List<Script> list();

    Script get(String symbol);

    void update(Long id, ScriptRequestDto request);
}
