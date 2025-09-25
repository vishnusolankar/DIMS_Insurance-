package com.dims.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private static final Logger log = LoggerFactory.getLogger(MyService.class);

    public void processData() {
        log.info("Process started...");
        try {
            // business logic
            int result = 100 / 5;
            log.debug("Calculated result = {}", result);
            log.info("Process finished successfully");
        } catch (Exception e) {
            log.error("Error during processing", e);
        }
    }
}
