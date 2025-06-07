package com.learning.utils;

import com.learning.config.AppProperties;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.endpoint.StaticEndpointBuilders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightEndpoints {

    private final AppProperties properties;

    public String fileUri() {
        return StaticEndpointBuilders
                .file(properties.getFile().getSourceDirectory())
                .move(properties.getFile().getProcessedDirectory())
                .delay(60000)
                .include(".*\\.xlsx$")
                .getUri();
    }

    public String kafkaUri() {
        return StaticEndpointBuilders
                .kafka(properties.getKafka().getTopic())
                .brokers(properties.getKafka().getBrokers())
                .getUri();
    }
}
