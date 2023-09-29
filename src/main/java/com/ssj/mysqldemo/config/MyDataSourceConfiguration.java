package com.ssj.mysqldemo.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class MyDataSourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("hikari.datasource")
    public DataSourceProperties firstProperties() {
        log.info("hikari properties");
        return new DataSourceProperties();
    }
    @Bean
    @ConfigurationProperties("dbcp2.datasource")
    public DataSourceProperties secondProperties() {
        log.info("dbcp2 properties");
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("hikari.datasource.advance")
    @Primary
    public DataSource hikariDataSource(@Qualifier("firstProperties") DataSourceProperties hikariProperties) {
        return hikariProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    @Bean
    @ConfigurationProperties("dbcp2.datasource.advance")
    // We must use @Qualifier since injection by TYPE take priority because of @Primary on DataSourceProperties
    public BasicDataSource basicDataSource() {
        BasicDataSource ds = secondProperties().initializeDataSourceBuilder().type(BasicDataSource.class).build();
        log.info("idle: {}, life time: {}", ds.getMaxIdle(), ds.getMaxConnLifetimeMillis());
        return ds;
    }


}
