package com.example.share.service;

import com.example.share.dto.request.CompanyRequestDto;
import com.example.share.model.Company;

import java.util.List;

public interface CompanyService {

    void create(CompanyRequestDto request);

    List<Company> list();

    Company get(String scrip);

    void update(Long id, CompanyRequestDto request);
}
