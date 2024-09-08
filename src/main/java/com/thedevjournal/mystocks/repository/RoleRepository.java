package com.thedevjournal.mystocks.repository;

import com.thedevjournal.mystocks.enums.RoleType;
import com.thedevjournal.mystocks.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleType name);
}
