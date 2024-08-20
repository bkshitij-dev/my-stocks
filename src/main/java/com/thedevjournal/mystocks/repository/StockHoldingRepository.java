package com.thedevjournal.mystocks.repository;

import com.thedevjournal.mystocks.model.StockHolding;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockHoldingRepository extends JpaRepository<StockHolding, Long> {

    @Modifying
    @Transactional
    @Query(value = "WITH Transactions AS (\n" +
            "    SELECT c.scrip as scrip,\n" +
            "        st.transaction_type as transaction_type,\n" +
            "        st.stock_type as stock_type,\n" +
            "        st.quantity as quantity,\n" +
            "        st.rate as rate,\n" +
            "        CASE\n" +
            "            WHEN st.transaction_type = 'BUY' THEN st.quantity * st.rate\n" +
            "            ELSE 0\n" +
            "        END AS buy_amount,\n" +
            "        CASE\n" +
            "            WHEN st.transaction_type = 'SELL' THEN st.quantity * st.rate\n" +
            "            ELSE 0\n" +
            "        END AS sell_amount\n" +
            "    FROM stock_transactions st\n" +
            "    INNER JOIN companies c ON st.company_id = c.id\n" +
            "    WHERE c.id = ?1\n" +
            "),\n" +
            "Aggregated AS (\n" +
            "    SELECT\n" +
            "        scrip,\n" +
            "        SUM(CASE WHEN transaction_type = 'BUY' THEN quantity ELSE 0 END) AS total_buy_quantity,\n" +
            "        SUM(CASE WHEN transaction_type = 'SELL' THEN quantity ELSE 0 END) AS total_sell_quantity,\n" +
            "        SUM(buy_amount) AS total_buy,\n" +
            "        SUM(sell_amount) AS total_sell,\n" +
            "        SUM(CASE\n" +
            "                WHEN transaction_type = 'BUY' THEN quantity ELSE 0 END) - SUM(CASE WHEN transaction_type = 'SELL'\n" +
            "                THEN quantity ELSE 0 END) AS current_quantity,\n" +
            "        SUM(buy_amount) - SUM(sell_amount) AS current_investment\n" +
            "    FROM Transactions\n" +
            "    GROUP BY scrip\n" +
            "),\n" +
            "Wacc AS (\n" +
            "    SELECT\n" +
            "        scrip,\n" +
            "        CASE\n" +
            "            WHEN current_quantity > 0 THEN\n" +
            "                CEIL((current_investment / current_quantity) * 100) / 100\n" +
            "            ELSE 0\n" +
            "        END AS wacc\n" +
            "    FROM Aggregated\n" +
            "),\n" +
            "Script AS (\n" +
            "    SELECT\n" +
            "        scrip,\n" +
            "        ltp,\n" +
            "        target\n" +
            "    FROM companies\n" +
            "    WHERE id = ?1\n" +
            ")\n" +
            "INSERT INTO stock_holdings (\n" +
            "    id,\n" +
            "    company_id,\n" +
            "    total_buy_quantity,\n" +
            "    total_sell_quantity,\n" +
            "    total_buy_amount,\n" +
            "    total_sell_amount,\n" +
            "    holding_quantity,\n" +
            "    current_investment,\n" +
            "    wacc\n" +
            ")\n" +
            "SELECT\n" +
            "    nextval('stock_holding_seq') AS id,\n" +
            "    c.id AS companyId,\n" +
            "    a.total_buy_quantity AS totalBuyQuantity,\n" +
            "    a.total_sell_quantity AS totalSellQuantity,\n" +
            "    a.total_buy AS totalBuy,\n" +
            "    a.total_sell AS totalSell,\n" +
            "    a.current_quantity AS currentQuantity,\n" +
            "    a.current_investment AS currentInvestment,\n" +
            "    COALESCE(w.wacc, 0) AS wacc\n" +
            "FROM Aggregated a\n" +
            "LEFT JOIN Wacc w ON a.scrip = w.scrip\n" +
            "INNER JOIN companies c ON c.scrip = a.scrip\n" +
            "ON CONFLICT (company_id)\n" +
            "DO UPDATE SET\n" +
            "    total_buy_quantity = EXCLUDED.total_buy_quantity,\n" +
            "    total_sell_quantity = EXCLUDED.total_sell_quantity,\n" +
            "    total_buy_amount = EXCLUDED.total_buy_amount,\n" +
            "    total_sell_amount = EXCLUDED.total_sell_amount,\n" +
            "    holding_quantity = EXCLUDED.holding_quantity,\n" +
            "    current_investment = EXCLUDED.current_investment,\n" +
            "    wacc = EXCLUDED.wacc;\n", nativeQuery = true)
    void updateOnStockTransaction(Long companyId);
}
