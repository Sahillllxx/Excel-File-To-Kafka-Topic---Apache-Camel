package com.learning.route;

import com.learning.model.Flight;
import com.learning.process.FlightExcelProcessor;
import com.learning.utils.FlightEndpoints;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class FlightFileToKafkaRoute extends EndpointRouteBuilder {

    private final FlightExcelProcessor excelProcessor;

    private final FlightEndpoints flightEndpoints;

    @Override
    public void configure() throws Exception {

        from(flightEndpoints.fileUri())

                .routeId("excel-to-kafka")
                     .log("Picked file: ${header.CamelFileName}")

            .process(excelProcessor)
                .log("File Processing !...")

                .split(body())
                .setHeader("kafka.KEY", simple("${body[flightNumber]}"))
                .marshal()
                .json(JsonLibrary.Jackson, Flight.class)

            .to(flightEndpoints.kafkaUri())
            .log("Pushed flight: ${body}")

        .end();
    }
}
