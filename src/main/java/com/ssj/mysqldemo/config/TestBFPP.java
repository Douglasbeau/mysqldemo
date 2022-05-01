package com.ssj.mysqldemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
// 在bean生成之前
public class TestBFPP implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("Bean Factory pp ");
        // 此时还没有任何property被赋值
//        MutablePropertyValues hikariDataSource = beanFactory.getBeanDefinition("hikariDataSource").getPropertyValues();
//        log.info("hikari.datasource.url: {}", hikariDataSource.get("hikari.datasource.url"));
    }
}
