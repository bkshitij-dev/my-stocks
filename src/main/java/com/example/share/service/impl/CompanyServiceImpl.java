package com.example.share.service.impl;

import com.example.share.dto.request.CompanyRequestDto;
import com.example.share.enums.Sector;
import com.example.share.model.Company;
import com.example.share.repository.CompanyRepository;
import com.example.share.service.CompanyService;
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
