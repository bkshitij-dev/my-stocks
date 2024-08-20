package com.thedevjournal.mystocks.service.impl;

import com.thedevjournal.mystocks.dto.request.CompanyRequestDto;
import com.thedevjournal.mystocks.dto.response.CompanyResponseDto;
import com.thedevjournal.mystocks.mapper.CompanyMapper;
import com.thedevjournal.mystocks.model.Company;
import com.thedevjournal.mystocks.model.Sector;
import com.thedevjournal.mystocks.repository.CompanyRepository;
import com.thedevjournal.mystocks.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public void create(CompanyRequestDto request) {
        Company company = Company.builder()
                .name(request.getName())
                .scrip(request.getScrip())
                .sector(Sector.builder().id(request.getSectorId()).build())
                .ltp(request.getLtp())
                .build();
        companyRepository.save(company);
    }

    @Override
    public List<CompanyResponseDto> list() {
        return companyMapper.list();
    }

    @Override
    public Company get(Long id) throws Exception {
        return companyRepository.findById(id).orElseThrow(() -> new Exception("Cannot find company"));
    }

    @Override
    public Company findByScrip(String scrip) throws Exception {
        return companyRepository.findByScrip(scrip)
                .orElseThrow(() -> new Exception("Cannot find company with scrip " + scrip));
    }

    @Override
    public boolean existsByScrip(String scrip) {
        return companyRepository.existsByScrip(scrip);
    }

    @Override
    public CompanyResponseDto getByScrip(String scrip) {
        return companyMapper.getByScrip(scrip);
    }

    @Override
    public void update(Long id, CompanyRequestDto request) {
        companyMapper.update(id, request);
    }
}
