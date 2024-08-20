package com.thedevjournal.mystocks.job;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

import com.thedevjournal.mystocks.service.StockHistoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LiveDataJob {

    private final StockHistoryService stockHistoryService;
    private static final Logger logger = LoggerFactory.getLogger(LiveDataJob.class);

    @Scheduled(cron = "0 */2 * ? * *")
    public void fetchLiveData() throws Exception {
        logger.info("Fetching live data >>>>>>");
        stockHistoryService.fetchLiveData();
        logger.info("<<<<<< Successfully fetched live data and updated stock history");
    }
}
