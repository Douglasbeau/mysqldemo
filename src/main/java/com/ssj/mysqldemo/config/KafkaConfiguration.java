package com.ssj.mysqldemo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Collections;
import java.util.List;

@Slf4j
//@Configuration
//@EnableKafka
public class KafkaConfiguration {
    @Value("${spring.xx.url}")
    private String url;
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("t0")
                .partitions(8)
                .replicas(1)
                .build();
    }
    @KafkaListener(id = "aaa", topics = "t0")
    public void listen(String in){
        log.info("received msg: {}", in);
    }

//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory, ConcurrentKafkaListenerContainerFactory<String, String> factory) {
//        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);
//        factory.setReplyTemplate(kafkaTemplate);
//        return kafkaTemplate;
//    }

    @Bean
    public ProducerFactory<Object, Object> producerFactory(KafkaProperties kafkaProperties){
        overwriteByGcs(kafkaProperties);
        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.kafka")
    KafkaProperties originKafkaProperties(){
        return new KafkaProperties();
    }

    private void overwriteByGcs(KafkaProperties kafkaProperties) {
        kafkaProperties.setBootstrapServers(Collections.singletonList("localhost:3333"));
        List<String> bootstrapServers = kafkaProperties.getBootstrapServers();

        log.info("changed kafka properties {} with url: {}", bootstrapServers.get(0), url);
        // if getServers()
//        kafkaProperties.setBootstrapServers(Arrays.asList("localhost:10000"));
    }


}
