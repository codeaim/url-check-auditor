package com.codeaim.urlcheck.probe.task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedule
{
    private static final Logger log = LoggerFactory.getLogger(TaskSchedule.class);

    @Autowired
    private CheckTask checkTask;
    @Autowired
    private ResultExpiryTask resultExpiryTask;

    @Scheduled(fixedRate = 2000)
    public void CheckTask()
    {
        long startResponseTime = System.currentTimeMillis();
        log.debug("Starting check task {}", LocalDateTime.now(ZoneOffset.UTC));
        checkTask.run();
        log.debug("Completed check task {}", LocalDateTime.now(ZoneOffset.UTC));
        log.debug("Check task time taken: {}", System.currentTimeMillis() - startResponseTime);
    }

    @Scheduled(fixedRate = 300000)
    public void ResultExpiryTask()
    {
        long startResponseTime = System.currentTimeMillis();
        log.debug("Starting result expiry task {}", LocalDateTime.now(ZoneOffset.UTC));
        resultExpiryTask.run();
        log.debug("Completed result expiry task {}", LocalDateTime.now(ZoneOffset.UTC));
        log.debug("Result expiry task time taken: {}", System.currentTimeMillis() - startResponseTime);
    }
}
