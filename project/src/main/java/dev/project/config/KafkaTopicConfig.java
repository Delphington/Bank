package dev.project.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static dev.project.util.ConstantsUtil.*;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic metrics() {
        return TopicBuilder.name(METRICS)
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic accounts() {
        return TopicBuilder.name(ACCOUNTS)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic transactions() {
        return TopicBuilder.name(TRANSACTIONS)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
