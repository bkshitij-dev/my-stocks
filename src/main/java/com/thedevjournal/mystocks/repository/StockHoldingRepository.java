package com.thedevjournal.mystocks.repository;

import com.thedevjournal.mystocks.model.StockHolding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockHoldingRepository extends JpaRepository<StockHolding, Long> {

    @Query(value = "MERGE INTO stock_holdings sh\n" +
            "USING(\n" +
            "WITH Transactions AS (\n" +
            "            SELECT c.scrip as scrip,\n" +
            "                st.transaction_type as transaction_type,\n" +
            "                st.stock_type as stock_type,\n" +
            "                st.quantity as quantity,\n" +
            "                st.rate as rate,\n" +
            "            CASE\n" +
            "                WHEN st.transaction_type = 'BUY' THEN st.quantity * st.rate\n" +
            "                    ELSE 0\n" +
            "                END AS buy_amount,\n" +
            "            CASE\n" +
            "                WHEN st.transaction_type = 'SELL' THEN st.quantity * st.rate\n" +
            "                    ELSE 0\n" +
            "                END AS sell_amount\n" +
            "            FROM stock_transactions st\n" +
            "            INNER JOIN companies c on st.company_id = c.id\n" +
            "            WHERE c.scrip = ?1\n" +
            "        ),\n" +
            "        Aggregated AS (\n" +
            "            SELECT\n" +
            "                scrip,\n" +
            "                SUM(CASE WHEN transaction_type = 'BUY' THEN quantity ELSE 0 END) AS total_buy_quantity,\n" +
            "                SUM(CASE WHEN transaction_type = 'SELL' THEN quantity ELSE 0 END) AS total_sell_quantity,\n" +
            "                SUM(buy_amount) AS total_buy,\n" +
            "                SUM(sell_amount) AS total_sell,\n" +
            "                SUM(CASE\n" +
            "                        WHEN transaction_type = 'BUY' THEN quantity ELSE 0 END) - SUM(CASE WHEN transaction_type = 'SELL'\n" +
            "                        THEN quantity ELSE 0 END) AS current_quantity,\n" +
            "                SUM(buy_amount) - SUM(sell_amount) AS current_investment\n" +
            "            FROM Transactions\n" +
            "            GROUP BY scrip\n" +
            "        ),\n" +
            "        Wacc AS (\n" +
            "            SELECT\n" +
            "                scrip,\n" +
            "                CASE\n" +
            "                    WHEN current_quantity > 0 THEN\n" +
            "                        CEIL((current_investment / current_quantity) * 100) / 100\n" +
            "                    ELSE 0\n" +
            "                END AS wacc\n" +
            "            FROM Aggregated\n" +
            "        ),\n" +
            "        Script AS (\n" +
            "            SELECT\n" +
            "                scrip,\n" +
            "                ltp,\n" +
            "                target\n" +
            "            FROM companies\n" +
            "            WHERE scrip = ?1\n" +
            "        )\n" +
            "        SELECT\n" +
            "            c.id as companyId,\n" +
            "            a.scrip as scrip,\n" +
            "            a.total_buy_quantity as totalBuyQuantity,\n" +
            "            a.total_sell_quantity as totalSellQuantity,\n" +
            "            a.total_buy as totalBuy,\n" +
            "            a.total_sell as totalSell,\n" +
            "            a.current_quantity as currentQuantity,\n" +
            "            a.current_investment as currentInvestment,\n" +
            "            COALESCE(w.wacc, 0) AS wacc\n" +
            "        FROM Aggregated a\n" +
            "        LEFT JOIN Wacc w ON a.scrip = w.scrip\n" +
            "        INNER JOIN companies c ON c.scrip = a.scrip\n" +
            ")q\n" +
            "ON sh.company_id = q.companyId\n" +
            "WHEN MATCHED THEN UPDATE\n" +
            "SET\n" +
            "   sh.total_buy_quantity = q.totalBuyQuantity,\n" +
            "   sh.total_sell_quantity = q.totalSellQuantity,\n" +
            "   sh.total_buy_amount = q.totalBuy,\n" +
            "   sh.total_sell_amount = q.totalSell,\n" +
            "   sh.holding_quantity = q.currentQuantity,\n" +
            "   sh.current_investment = q.currentInvestment,\n" +
            "   sh.wacc = q.wacc\n" +
            "WHEN NOT MATCHED THEN\n" +
            "INSERT(id, company_id, total_buy_quantity, total_sell_quantity, total_buy_amount, total_sell_amount,\n" +
            "holding_quantity, current_investment, wacc)\n" +
            "VALUES (stock_holding_seq.nextval, q.companyId, q.totalBuyQuantity, q.totalSellQuantity,\n" +
            "q.totalBuy, q.totalSell, q.currentQuantity, q.currentInvestment, q.wacc)", nativeQuery = true)
    void updateOnStockTransaction(String scrip);
}
