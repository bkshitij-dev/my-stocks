package com.thedevjournal.mystocks.model;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stock_market_history",
        uniqueConstraints = @UniqueConstraint(name = "uk_stockmarkethistory_date", columnNames = {"date"}))
public class StockMarketHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_market_history_seq")
    @SequenceGenerator(name = "stock_market_history_seq", sequenceName = "stock_market_history_seq", allocationSize = 1)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "index", nullable = false)
    private BigDecimal index;

    @Column(name = "percentage_change", nullable = false)
    private BigDecimal percentageChange;
}
