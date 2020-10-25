package com.freenow.carmgmt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FreeNowCarManagementApplicationTests
{
    private static final Logger LOG = LoggerFactory.getLogger(FreeNowCarManagementApplicationTests.class);

    @Test
    @DisplayName("contextLoads Test")
    void contextLoads()
    {
        LOG.info("Context load successful");
    }

}
