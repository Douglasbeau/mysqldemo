package com.ssj.mysqldemo;

import com.ssj.mysqldemo.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
@Configuration
public class MysqldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqldemoApplication.class, args);
    }

}
