package com.zhzhgang.order.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhzhgang
 * 2017-10-03 22:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {

    // private final Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test1() {
        String name = "zhzhgang";
        String password = "123456";
        log.debug("debug");
        log.info("name: {}, password: {}", name, password);
        log.error("error");
    }
}
