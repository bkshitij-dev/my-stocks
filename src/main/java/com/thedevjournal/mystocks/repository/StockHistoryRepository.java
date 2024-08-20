package com.thedevjournal.mystocks.repository;

import com.thedevjournal.mystocks.model.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

@Repository
public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {
}
