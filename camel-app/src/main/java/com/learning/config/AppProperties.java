package com.learning.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private FileProperties file;
    private KafkaProperties kafka;

    @Data
    public static class FileProperties {
        private String sourceDirectory;
        private String processedDirectory;
    }
    @Data
    public static class KafkaProperties {
        private String topic;
        private String brokers;
    }
}
