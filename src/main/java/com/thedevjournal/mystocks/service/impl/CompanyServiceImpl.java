package com.thedevjournal.mystocks.service.impl;

import com.thedevjournal.mystocks.dto.request.CompanyRequestDto;
import com.thedevjournal.mystocks.enums.Sector;
import com.thedevjournal.mystocks.model.Company;
import com.thedevjournal.mystocks.repository.CompanyRepository;
import com.thedevjournal.mystocks.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public void create(CompanyRequestDto request) {
        Company company = Company.builder()
                .name(request.getName())
                .scrip(request.getScrip())
                .sector(Sector.valueOf(request.getSector()))
                .ltp(request.getLtp())
                .build();
        companyRepository.save(company);
    }

    @Override
    public List<Company> list() {
        return companyRepository.findAll();
    }

    @Override
    public Company get(String scrip) {
        return companyRepository.findByScrip(scrip);
    }

    @Override
    public void update(Long id, CompanyRequestDto request) {
        Company company = Company.builder()
                .id(id)
                .name(request.getName())
                .scrip(request.getScrip())
                .sector(Sector.valueOf(request.getSector()))
                .ltp(request.getLtp())
                .build();
        companyRepository.save(company);
    }
}
