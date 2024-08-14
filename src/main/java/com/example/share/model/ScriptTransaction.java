package com.example.share.model;

import com.example.share.enums.ShareType;
import com.example.share.enums.TransactionType;
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
@Table(name = "script_transactions")
public class ScriptTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "script_transaction_seq")
    @SequenceGenerator(name = "script_transaction_seq", sequenceName = "script_transaction_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "script_id", nullable = false)
    private Script script;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "share_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ShareType shareType;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;
}
