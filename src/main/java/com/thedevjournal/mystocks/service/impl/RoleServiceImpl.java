package com.thedevjournal.mystocks.service.impl;

import com.thedevjournal.mystocks.dto.request.RoleRequestDto;
import com.thedevjournal.mystocks.enums.RoleType;
import com.thedevjournal.mystocks.model.Role;
import com.thedevjournal.mystocks.repository.RoleRepository;
import com.thedevjournal.mystocks.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void create(RoleRequestDto request) {
        Role role = Role.builder()
                .name(RoleType.valueOf(request.getName()))
                .build();
        roleRepository.save(role);
    }

    @Override
    public Role findByName(RoleType name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Long count() {
        return roleRepository.count();
    }
}
