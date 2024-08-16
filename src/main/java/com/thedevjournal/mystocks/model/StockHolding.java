package com.thedevjournal.mystocks.model;

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
@Table(name = "stock_holdings",
        uniqueConstraints = @UniqueConstraint(name = "uk_stockholding_company", columnNames = {"company_id"}))
public class StockHolding {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_holding_seq")
    @SequenceGenerator(name = "stock_holding_seq", sequenceName = "stock_holding_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "total_buy_quantity", nullable = false)
    private Integer totalBuyQuantity;

    @Column(name = "total_sell_quantity", nullable = false)
    private Integer totalSellQuantity;

    @Column(name = "total_buy_amount", nullable = false)
    private BigDecimal totalBuyAmount;

    @Column(name = "total_sell_amount", nullable = false)
    private BigDecimal totalSellAmount;

    @Column(name = "holding_quantity", nullable = false)
    private Integer holdingQuantity;

    @Column(name = "current_investment", nullable = false)
    private BigDecimal currentInvestment;

    @Column(name = "wacc", nullable = false)
    private BigDecimal wacc;
}
