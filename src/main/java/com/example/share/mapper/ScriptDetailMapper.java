package com.example.share.mapper;

import com.example.share.dto.response.ScriptDetailResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScriptDetailMapper {

    List<ScriptDetailResponseDto> groupByScrip();
}
