package com.ssj.mysqldemo;

import com.ssj.mysqldemo.config.MyDataSourceConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@SpringBootTest
@ContextConfiguration(classes = {MysqldemoApplication.class})
@PropertySource("/application.properties")
class MysqldemoApplicationTests {

    @Autowired
    ApplicationContext applicationContext;
    @Test
    void contextLoads() {
//        DataSource hikariDataSource = applicationContext.getBean("hikariDataSource", DataSource.class);
//        Assert.notNull(hikariDataSource, "null ds");

        log.info("end");
    }

}
