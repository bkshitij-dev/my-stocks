package com.thedevjournal.mystocks.model;

import com.thedevjournal.mystocks.enums.StockType;
import com.thedevjournal.mystocks.enums.TransactionType;
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
@Table(name = "stock_transactions")
public class StockTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_transaction_seq")
    @SequenceGenerator(name = "stock_transaction_seq", sequenceName = "stock_transaction_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "stock_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StockType stockType;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;
}
