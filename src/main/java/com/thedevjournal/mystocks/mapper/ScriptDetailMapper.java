package com.thedevjournal.mystocks.mapper;

import com.thedevjournal.mystocks.dto.response.ScriptDetailResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScriptDetailMapper {

    List<ScriptDetailResponseDto> groupByScrip();
}
