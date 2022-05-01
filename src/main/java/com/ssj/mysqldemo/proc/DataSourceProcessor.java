package com.ssj.mysqldemo.proc;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
public class DataSourceProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            log.info("datasource --" + beanName);
        }
        if (bean instanceof HikariDataSource) {
            log.info("set password");
            ((HikariDataSource) bean).setPassword("123456");
            ((HikariDataSource) bean).setUsername("root");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
