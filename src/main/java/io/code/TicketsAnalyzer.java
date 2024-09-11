package io.code;

import io.code.model.Ticket;
import io.code.util.Calculator;
import io.code.util.Parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketsAnalyzer {
    public static void main(String[] args) throws IOException {
        // Путь к файлу tickets.json
        var ticketsPath = "src/main/resources/tickets.json";

        // Инициализируем парсер
        var parser = new Parser();

        // Парсим билеты
        var ticketsData = parser.parseTickets(ticketsPath);

        // Собираем в коллекцию содержание билетов Владивосток - Тель-Авив
        List<Ticket> tickets = ticketsData.tickets.stream()
                .filter(t -> t.originName.equals("Владивосток") && t.destinationName.equals("Тель-Авив"))
                .collect(Collectors.toList());

        // Инициализируем калькулятор
        Calculator calculator = new Calculator();

        // Получаем минимальное время полета для каждого перевозчика и выводим его в консоль
        Map<String, Long> minFlightTimesByCarrier = calculator.calculateMinFlightTimes(tickets);
        System.out.println("Минимальное время полета для каждого авиаперевозчика:");
        for (Map.Entry<String, Long> entry : minFlightTimesByCarrier.entrySet()) {
            System.out.printf("Перевозчик %s: %d минут\n", entry.getKey(), entry.getValue());
        }

        // Получаем среднюю цену
        var averagePrice = calculator.calculateAveragePrice(tickets);

        // Получаем медианную цену
        var medianPrice = calculator.calculateMedianPrice(tickets);

        // Получаем разницу между средней ценой и медианой
        var priceDifference = calculator.calculatePriceDifference(tickets);

        // Выводим результаты полученнных средней цены и медианы в консоль
        System.out.println("\nЦены на билеты Владивосток - Тель-Авив:");
        System.out.printf("Средняя цена: %.2f\n", averagePrice);
        System.out.printf("Медиана цены: %.2f\n", medianPrice);
        System.out.printf("Разница между средней ценой и медианой: %.2f\n", priceDifference);
    }
}
