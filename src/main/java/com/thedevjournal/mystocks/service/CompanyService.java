package com.thedevjournal.mystocks.service;

import com.thedevjournal.mystocks.dto.request.CompanyRequestDto;
import com.thedevjournal.mystocks.model.Company;

import java.util.List;

public interface CompanyService {

    void create(CompanyRequestDto request);

    List<Company> list();

    Company get(String scrip);

    void update(Long id, CompanyRequestDto request);
}
