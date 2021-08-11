package com.ssj.restclient;

import com.ssj.restclient.model.Student;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestclientApplication {
    private static final Logger LOGGER = org.apache.log4j.Logger.getLogger(RestclientApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(RestclientApplication.class, args);
    }
    @Value("${url}")
    private String url;
    @Value("${my.url}")
    private String myUrl;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    // use restTemplate to perform a http get request
    @Bean
    public CommandLineRunner run (RestTemplate restTemplate) throws Exception{
        return args -> {
            Student student = restTemplate.getForObject(myUrl, Student.class);
            LOGGER.info("Got student: " + student.toString());
        };
    }

}
