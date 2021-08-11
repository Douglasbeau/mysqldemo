package com.ssj.mysqldemo;

import com.ssj.mysqldemo.config.MyDataSourceConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import javax.sql.DataSource;

@SpringBootTest
@ContextConfiguration(classes = {MysqldemoApplication.class})
@PropertySource("/application.properties")
class MysqldemoApplicationTests {

    @Autowired
    ApplicationContext applicationContext;
    @Test
    void contextLoads() {
        DataSource hikariDataSource = applicationContext.getBean("hikariDataSource", DataSource.class);
        Assert.notNull(hikariDataSource, "null ds");
    }

}
