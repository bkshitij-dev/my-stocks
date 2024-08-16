package com.example.share.controller;

import com.example.share.dto.request.CompanyRequestDto;
import com.example.share.dto.response.CompanyResponseDto;
import com.example.share.model.Company;
import com.example.share.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public String create(@RequestBody CompanyRequestDto request) {
        companyService.create(request);
        return "SUCCESS";
    }

    @GetMapping
    public List<CompanyResponseDto> list() {
        List<CompanyResponseDto> response = new ArrayList<>();
        List<Company> companies = companyService.list();
        companies.forEach(company -> {
            response.add(prepare(company));
        });
        return response;
    }

    @GetMapping("/{scrip}")
    public CompanyResponseDto get(@PathVariable("scrip") String scrip) {
        Company company = companyService.get(scrip);
        return prepare(company);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody CompanyRequestDto request) {
        companyService.update(id, request);
        return "SUCCESS";
    }

    private CompanyResponseDto prepare(Company company) {
        return CompanyResponseDto.builder()
                .id(company.getId())
                .name(company.getName())
                .scrip(company.getScrip())
                .sector(company.getSector().name())
                .ltp(company.getLtp())
                .build();
    }
}
