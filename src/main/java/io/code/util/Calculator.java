package io.code.util;

import io.code.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Calculator {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");

    // Вычисляем минимальное время полета для каждого перевозчика
    public Map<String, Long> calculateMinFlightTimes(List<Ticket> tickets) {
        Map<String, Long> minFlightTimesByCarrier = new HashMap<>();

        for (var ticket : tickets) {
            // Расчет времени вылета
            var departureTime = LocalDateTime.parse(
                    ticket.departureDate
                         + " "
                         + ticket.departureTime, DATE_TIME_FORMATTER
            );

            // Расчет времени прибытия
            var arrivalTime = LocalDateTime.parse(
                    ticket.arrivalDate
                    + " "
                    + ticket.arrivalTime, DATE_TIME_FORMATTER
            );

            // Считаем время полета
            var flightTimeInMinutes = Duration.between(departureTime, arrivalTime).toMinutes();

            // Собираем мапу с указанием минимального времени полета для каждого перевозчика
            minFlightTimesByCarrier.merge(ticket.carrier, flightTimeInMinutes, Math::min);
        }

        return minFlightTimesByCarrier;
    }

    // Находим среднюю цену
    public double calculateAveragePrice(List<Ticket> tickets) {
        return tickets.stream().mapToInt(ticket -> ticket.price).average().orElse(0);
    }

    // Находим медиану
    public double calculateMedianPrice(List<Ticket> tickets) {
        // Получаем список цен
        List<Integer> prices = tickets.stream()
                .map(ticket -> ticket.price)
                .sorted()
                .collect(Collectors.toList());

        // Получаем размер списка цен
        var size = prices.size();

        // Проверяем четность списка цен (количества цен) и находим медиану: если количество четное, то медианой
        // является среднее арифметическое двух средних элементов, а если нечетное – то средний элемент
        if (size % 2 == 0) {
            return (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            return prices.get(size / 2);
        }
    }

    // Получаем разницу между средней ценой и медианой
    public double calculatePriceDifference(List<Ticket> tickets) {
        // Получаем среднюю цену
        var averagePrice = calculateAveragePrice(tickets);

        // Получаем медиану
        var medianPrice = calculateMedianPrice(tickets);

        return averagePrice - medianPrice;
    }
}
