package com.thedevjournal.mystocks.mapper;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

import com.thedevjournal.mystocks.dto.request.SectorRequestDto;
import com.thedevjournal.mystocks.dto.response.SectorResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SectorMapper {

    List<SectorResponseDto> list();

    SectorResponseDto get(@Param("id") Long id);

    void update(@Param("id") Long id, @Param("request") SectorRequestDto request);
}
