package com.thedevjournal.mystocks.mapper;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

import com.thedevjournal.mystocks.dto.request.CompanyRequestDto;
import com.thedevjournal.mystocks.dto.response.CompanyResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMapper {

    List<CompanyResponseDto> list();

    CompanyResponseDto getByScrip(@Param("scrip") String scrip);

    void update(@Param("id") Long id, @Param("request") CompanyRequestDto request);
}
