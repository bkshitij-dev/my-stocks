package com.thedevjournal.mystocks.config;

import com.thedevjournal.mystocks.job.LiveDataJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(LiveDataJob.class)
                .withIdentity("liveDataJob")
                .storeDurably()
                .build();
    }

    // Runs every Sunday to Thursday every minute from 11:00 am to 2:59 pm
    @Bean
    public Trigger onMarketTrigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("onMarketTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0-59 11-14 ? * 1,2,3,4,5"))
                .build();
    }

    // Runs every Sunday to Thursday every minute from 3:00 pm to 3:05 pm
    // To update after market closed, for possible delay in updates
    @Bean
    public Trigger offMarketTrigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("offMarketTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0-5 15 ? * 1,2,3,4,5"))
                .build();
    }
}
