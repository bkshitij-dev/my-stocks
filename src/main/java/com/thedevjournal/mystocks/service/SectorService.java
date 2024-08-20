package com.thedevjournal.mystocks.service;

import com.thedevjournal.mystocks.dto.request.SectorRequestDto;
import com.thedevjournal.mystocks.dto.response.SectorResponseDto;

import java.util.List;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */
public interface SectorService {

    void create(SectorRequestDto request);

    List<SectorResponseDto> list();

    SectorResponseDto get(Long id);

    void update(Long id, SectorRequestDto request);
}
