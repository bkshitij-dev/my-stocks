package com.example.share.repository;

import com.example.share.model.ScriptTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScriptTransactionRepository extends JpaRepository<ScriptTransaction, Long> {

    @Query(value = "SELECT st.id, " +
            "st.script_id, " +
            "st.transaction_type, " +
            "st.share_type, " +
            "st.quantity, " +
            "st.rate " +
            "FROM script_transactions st " +
            "INNER JOIN scripts s ON st.script_id = s.id " +
            "WHERE s.symbol = ?1", nativeQuery = true)
    List<ScriptTransaction> findAllByScript(String symbol);
}
