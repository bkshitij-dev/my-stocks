package com.thedevjournal.mystocks.service;

import com.thedevjournal.mystocks.dto.request.RoleRequestDto;
import com.thedevjournal.mystocks.enums.RoleType;
import com.thedevjournal.mystocks.model.Role;

public interface RoleService {

    void create(RoleRequestDto request);

    Role findByName(RoleType name);

    Long count();
}
