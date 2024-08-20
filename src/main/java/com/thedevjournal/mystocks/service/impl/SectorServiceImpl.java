package com.thedevjournal.mystocks.service.impl;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

import com.thedevjournal.mystocks.dto.request.SectorRequestDto;
import com.thedevjournal.mystocks.dto.response.SectorResponseDto;
import com.thedevjournal.mystocks.mapper.SectorMapper;
import com.thedevjournal.mystocks.model.Sector;
import com.thedevjournal.mystocks.repository.SectorRepository;
import com.thedevjournal.mystocks.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectorServiceImpl implements SectorService {

    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    @Override
    public void create(SectorRequestDto request) {
        Sector sector = Sector.builder()
                .name(request.getName())
                .build();
        sectorRepository.save(sector);
    }

    @Override
    public List<SectorResponseDto> list() {
        return sectorMapper.list();
    }

    @Override
    public SectorResponseDto get(Long id) {
        return sectorMapper.get(id);
    }

    @Override
    public void update(Long id, SectorRequestDto request) {
        sectorMapper.update(id, request);
    }
}
