package com.example.share.repository;

import com.example.share.model.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {

    @Query(value = "SELECT st.id, " +
            "st.company_id, " +
            "st.transaction_type, " +
            "st.stock_type, " +
            "st.quantity, " +
            "st.rate " +
            "FROM stock_transactions st " +
            "INNER JOIN companies c ON st.company_id = c.id " +
            "WHERE c.scrip = ?1", nativeQuery = true)
    List<StockTransaction> findAllByScrip(String scrip);
}
