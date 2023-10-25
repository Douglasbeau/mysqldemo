package com.ssj.mysqldemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
//@SpringBootTest
@ContextConfiguration(classes = {MysqldemoApplication.class})
@PropertySource("/application.properties")
public class MysqldemoApplicationTests {

    @Autowired
    ApplicationContext applicationContext;
//    @Test
    void contextLoads() {
//        DataSource hikariDataSource = applicationContext.getBean("hikariDataSource", DataSource.class);
//        Assert.notNull(hikariDataSource, "null ds");

        log.info("end");
    }

}
