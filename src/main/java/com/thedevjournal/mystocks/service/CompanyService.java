package com.thedevjournal.mystocks.service;

import com.thedevjournal.mystocks.dto.request.CompanyRequestDto;
import com.thedevjournal.mystocks.dto.response.CompanyResponseDto;
import com.thedevjournal.mystocks.model.Company;

import java.util.List;

public interface CompanyService {

    void create(CompanyRequestDto request);

    List<CompanyResponseDto> list();

    Company get(Long id) throws Exception;

    Company findByScrip(String scrip) throws Exception;

    boolean existsByScrip(String scrip);

    CompanyResponseDto getByScrip(String scrip);

    void update(Long id, CompanyRequestDto request);
}
