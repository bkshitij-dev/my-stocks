package com.thedevjournal.mystocks.service;

import com.thedevjournal.mystocks.dto.request.LoginRequestDto;
import com.thedevjournal.mystocks.dto.request.RegisterRequestDto;
import com.thedevjournal.mystocks.dto.response.LoginResponseDto;
import com.thedevjournal.mystocks.enums.RoleType;

import java.util.List;

public interface UserService {

    LoginResponseDto login(LoginRequestDto request);

    void create(RegisterRequestDto request, List<RoleType> roles);

    void register(RegisterRequestDto request);

    Long count();
}
