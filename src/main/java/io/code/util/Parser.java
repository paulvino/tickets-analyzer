package io.code.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.code.model.TicketsData;

import java.io.File;
import java.io.IOException;

public class Parser {
    // Получаем информацию из JSON файла
    public TicketsData parseTickets(String filePath) throws IOException {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), new TypeReference<TicketsData>() { });
    }
}
