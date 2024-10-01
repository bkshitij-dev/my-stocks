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
        uniqueConstraints = @UniqueConstraint(name = "uk_stockhistory_company_stockmarkethistory",
                columnNames = {"company_id", "stock_market_history_id"}))
public class StockHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_history_seq")
    @SequenceGenerator(name = "stock_history_seq", sequenceName = "stock_history_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false, foreignKey = @ForeignKey(name = "fk_stockhistory_company"))
    private Company company;

    @Column(name = "ltp", nullable = false)
    private BigDecimal ltp;

    @Column(name = "points_change", nullable = false)
    private BigDecimal pointsChange;

    @Column(name = "percentage_change", nullable = false)
    private BigDecimal percentageChange;

    @Column(name = "open", nullable = false)
    private BigDecimal open;

    @Column(name = "high", nullable = false)
    private BigDecimal high;

    @Column(name = "low", nullable = false)
    private BigDecimal low;

    @Column(name = "volume")
    private BigDecimal volume;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_market_history_id", nullable = false, foreignKey = @ForeignKey(name = "fk_stockhistory_stockmarkethistory"))
    private StockMarketHistory stockMarketHistory;
}
