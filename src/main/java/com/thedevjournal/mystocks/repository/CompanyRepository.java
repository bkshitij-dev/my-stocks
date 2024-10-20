package com.thedevjournal.mystocks.repository;

import com.thedevjournal.mystocks.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "SELECT EXISTS (\n" +
            "    SELECT 1\n" +
            "    FROM companies\n" +
            "    WHERE scrip = ?1\n" +
            ")", nativeQuery = true)
    boolean existsByScrip(String scrip);

    Optional<Company> findByScrip(String scrip);

    @Query(value = "SELECT c.* " +
            "FROM companies c " +
            "INNER JOIN sectors s ON c.sector_id = s.id " +
            "WHERE s.name NOT IN ('Mutual Fund', 'Corporate Debentures', 'Government Bonds', 'Capital')",
            nativeQuery = true)
    List<Company> listMajor();
}
