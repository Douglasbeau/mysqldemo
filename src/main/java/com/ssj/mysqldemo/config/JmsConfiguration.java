package com.ssj.mysqldemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

@Slf4j
@Configuration
public class JmsConfiguration {

    @JmsListener(destination = "que")
    public void processMsg(String msg) {
        log.info("received message: {}", msg);
    }

}
