package com.thedevjournal.mystocks.job;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

import com.thedevjournal.mystocks.service.StockHistoryService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LiveDataJob implements Job {

    private final StockHistoryService stockHistoryService;
    private static final Logger logger = LoggerFactory.getLogger(LiveDataJob.class);

    @Override
    public void execute(JobExecutionContext context) {
        logger.info("Fetching live data >>>>>>");
        try {
            stockHistoryService.fetchLiveData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.info("<<<<<< Successfully fetched live data and updated stock history");
    }
}
