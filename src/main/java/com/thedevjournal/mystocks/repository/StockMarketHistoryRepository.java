package com.thedevjournal.mystocks.repository;

import com.thedevjournal.mystocks.model.StockMarketHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

@Repository
public interface StockMarketHistoryRepository extends JpaRepository<StockMarketHistory, Long> {
}
