package com.learning.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;

@Component
public class FlightExcelProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {

        InputStream is = exchange.getIn().getBody(InputStream.class);
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheetAt(0);

        List<Map<String, Object>> flightDataList = new ArrayList<>();
        Row headerRow = sheet.getRow(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            Map<String, Object> flightData = new LinkedHashMap<>();

            for (int j = 0; j < headerRow.getLastCellNum(); j++) {

                String key = headerRow.getCell(j).getStringCellValue();
                Cell cell = row.getCell(j);
                Object value = switch (cell.getCellType()) {
                    case STRING -> cell.getStringCellValue();
                    case NUMERIC -> cell.getNumericCellValue();
                    case BOOLEAN -> cell.getBooleanCellValue();
                    default -> null;
                };

                flightData.put(key, value);
            }

            flightDataList.add(flightData);
        }

        exchange.getIn().setBody(flightDataList);
    }
}
