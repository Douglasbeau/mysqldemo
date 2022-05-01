package com.ssj.mysqldemo;

import com.ssj.mysqldemo.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import java.util.Enumeration;
import java.util.HashMap;

@SpringBootApplication
@EnableJdbcRepositories
@Configuration
// 自动扫描Mapper
@MapperScan("com.ssj.mysqldemo.mapper")
@Slf4j
public class MysqldemoApplication  implements CommandLineRunner{

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    public static void main(String[] args) {
        log.info("start application...");
        log.info("get app home: {}", FileUtil.getAppHome());
        SpringApplication.run(MysqldemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("start sending message");
        jmsMessagingTemplate.send("que", new Message<String>() {
            @Override
            public String getPayload() {
                return "hello";
            }

            @Override
            public MessageHeaders getHeaders() {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("a", "1");
                return new MessageHeaders(hashMap);
            }
        });
    }
}
