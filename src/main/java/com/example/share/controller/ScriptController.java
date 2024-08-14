package com.example.share.controller;

import com.example.share.dto.request.ScriptRequestDto;
import com.example.share.dto.response.ScriptResponseDto;
import com.example.share.model.Script;
import com.example.share.service.ScriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/scripts")
@RequiredArgsConstructor
public class ScriptController {

    private final ScriptService scriptService;

    @PostMapping
    public String create(@RequestBody ScriptRequestDto request) {
        scriptService.create(request);
        return "SUCCESS";
    }

    @GetMapping
    public List<ScriptResponseDto> list() {
        List<ScriptResponseDto> response = new ArrayList<>();
        List<Script> scripts = scriptService.list();
        scripts.forEach(script -> {
            response.add(prepare(script));
        });
        return response;
    }

    @GetMapping("/{symbol}")
    public ScriptResponseDto get(@PathVariable("symbol") String symbol) {
        Script script = scriptService.get(symbol);
        return prepare(script);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody ScriptRequestDto request) {
        scriptService.update(id, request);
        return "SUCCESS";
    }

    private ScriptResponseDto prepare(Script script) {
        return ScriptResponseDto.builder()
                .id(script.getId())
                .name(script.getName())
                .symbol(script.getSymbol())
                .sector(script.getSector().name())
                .ltp(script.getLtp())
                .build();
    }
}
