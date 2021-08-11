package com.ssj.mysqldemo.config;

import com.ssj.mysqldemo.model.Hobby;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class MyDataSourceConfiguration {
//    First method - use @Resource
/*    @Resource
    private DataSourceProperties dataSourceProperties;

    @Bean
    @Primary
    public DataSource dataSource() {
        System.out.println("spring datasource");
        DataSourceBuilder<?> builder = dataSourceProperties.initializeDataSourceBuilder();
        DataSource ds = builder.build();
        return ds;
    }*/
    @Bean
    @Primary
    @ConfigurationProperties("hikari.datasource")
    public DataSourceProperties firstProperties() {
        System.out.println("datasource properties");
        return new DataSourceProperties();
    }
    @Bean
    @ConfigurationProperties("dbcp2.datasource")
    public DataSourceProperties secondProperties() {
        System.out.println("datasource properties");
        return new DataSourceProperties();
    }
// Second method - use self defined DataSourceProperties and default other properties
/*

    @Bean
    public DataSource secondDataSource() {
        DataSourceProperties dataSourceProperties = dataSourceProperties();
        DataSourceBuilder<?> dataSourceBuilder = dataSourceProperties.initializeDataSourceBuilder();
        System.out.println("app datasource");
        DataSource ds = dataSourceBuilder.build();
        return ds;
    }*/

    // Third method - use specific data source

    // properties -> ds
    @Bean
    @ConfigurationProperties("hikari.datasource.advance")
    @Primary
    public DataSource hikariDataSource(@Qualifier("firstProperties") DataSourceProperties hikariProperties) {
        HikariDataSource ds = hikariProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//        DataSource ds = DataSourceBuilder.create().build();
        return ds;
    }
    @Bean
    @ConfigurationProperties("dbcp2.datasource.advance")
    public BasicDataSource basicDataSource(DataSourceProperties secondProperties) {
        BasicDataSource ds = secondProperties.initializeDataSourceBuilder().type(BasicDataSource.class).build();
        System.out.println(ds.getMaxIdle());
        System.out.println(ds.getMaxConnLifetimeMillis());
        return ds;
    }
//
//    @Bean(name = "thirdDS")
//    @ConfigurationProperties("third")
//    public HikariDataSource thirdDS() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
    // JdbcTemplate
    @Bean
    @Primary
    public JdbcTemplate hikariJdbcTemplate(@Qualifier("hikariDataSource") DataSource hikariDataSource) {
        return new JdbcTemplate(hikariDataSource);
    }

    @Bean
    public JdbcTemplate dbcp2JdbcTemplate(@Qualifier("basicDataSource") DataSource basicDataSource) {
        return new JdbcTemplate(basicDataSource);
    }

}
