package com.thedevjournal.mystocks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stock_history",
        uniqueConstraints = @UniqueConstraint(name = "uk_stockhistory_company_date",
                columnNames = {"company_id", "date"}))
public class StockHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_history_seq")
    @SequenceGenerator(name = "stock_history_seq", sequenceName = "stock_history_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "ltp", nullable = false)
    private BigDecimal ltp;
}
