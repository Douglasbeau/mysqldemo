package com.ssj.mysqldemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

@Slf4j
//@Configuration
public class JmsConfiguration {

    @JmsListener(destination = "que")
    public void processMsg(String msg) {
        log.info("received message: {}", msg);
    }

}
