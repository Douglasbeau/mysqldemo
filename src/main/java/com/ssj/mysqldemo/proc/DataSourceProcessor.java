package com.ssj.mysqldemo.proc;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            System.out.println("datasource --" + beanName);
        }
        if (bean instanceof HikariDataSource) {
            ((HikariDataSource) bean).setPassword("123456");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
