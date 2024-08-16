package com.thedevjournal.mystocks.model;

import com.thedevjournal.mystocks.enums.Sector;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "companies", uniqueConstraints = @UniqueConstraint(name = "uk_company_scrip", columnNames = {"scrip"}))
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "scrip", nullable = false)
    private String scrip;

    @Column(name = "sector", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Sector sector;

    @Column(name = "ltp")
    private BigDecimal ltp;

    @Column(name = "target")
    private BigDecimal target;
}
