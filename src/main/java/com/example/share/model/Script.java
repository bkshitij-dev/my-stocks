package com.example.share.model;

import com.example.share.enums.Sector;
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
@Table(name = "scripts", uniqueConstraints = @UniqueConstraint(name = "uk_script_symbol", columnNames = {"symbol"}))
public class Script {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "script_seq")
    @SequenceGenerator(name = "script_seq", sequenceName = "script_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "sector", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Sector sector;

    @Column(name = "ltp")
    private BigDecimal ltp;

    @Column(name = "target")
    private BigDecimal target;
}
