package com.thedevjournal.mystocks.controller;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

import com.thedevjournal.mystocks.dto.request.SectorRequestDto;
import com.thedevjournal.mystocks.dto.response.SectorResponseDto;
import com.thedevjournal.mystocks.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sectors")
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    @PostMapping
    public String create(@RequestBody SectorRequestDto request) {
        sectorService.create(request);
        return "SUCCESS";
    }

    @GetMapping
    public List<SectorResponseDto> list() {
        return sectorService.list();
    }

    @GetMapping("/{id}")
    public SectorResponseDto get(@PathVariable("id") Long id) {
        return sectorService.get(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody SectorRequestDto request) {
        sectorService.update(id, request);
        return "SUCCESS";
    }
}
