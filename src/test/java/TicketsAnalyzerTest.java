import io.code.util.Parser;
import io.code.util.Calculator;
import io.code.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TicketsAnalyzerTest {

    private List<Ticket> tickets;
    private Calculator calculator;

    @BeforeEach
    public void setUp() throws IOException {
        tickets = new ArrayList<>();
        Ticket ticket1 = new Ticket();
        ticket1.origin = "VVO";
        ticket1.destination = "TLV";
        ticket1.departureDate = "12.05.18";
        ticket1.departureTime = "16:20";
        ticket1.arrivalDate = "12.05.18";
        ticket1.arrivalTime = "22:10";
        ticket1.carrier = "TK";
        ticket1.price = 12400;

        Ticket ticket2 = new Ticket();
        ticket2.origin = "VVO";
        ticket2.destination = "TLV";
        ticket2.departureDate = "12.05.18";
        ticket2.departureTime = "06:10";
        ticket2.arrivalDate = "12.05.18";
        ticket2.arrivalTime = "16:15";
        ticket2.carrier = "S7";
        ticket2.price = 17400;

        tickets.add(ticket1);
        tickets.add(ticket2);

        calculator = new Calculator();
    }

    @Test
    public void testParse() throws IOException {
        var testParser = new Parser();
        var testTicketsPath = "src/test/resources/tickets.json";

        var ticketsData = testParser.parseTickets(testTicketsPath);

        assertThat(ticketsData.tickets).isNotNull();
        assertThat(ticketsData.tickets).hasSize(2);

        assertThat(ticketsData.tickets.get(0).originName).isEqualTo("Владивосток");
        assertThat(ticketsData.tickets.get(0).destinationName).isEqualTo("Тель-Авив");
        assertThat(ticketsData.tickets.get(0).price).isEqualTo(12400);

        assertThat(ticketsData.tickets.get(1).carrier).isEqualTo("S7");
        assertThat(ticketsData.tickets.get(1).price).isEqualTo(17400);
    }

    @Test
    public void testCalculateMinFlightTime() {
        var minFlightTimes = calculator.calculateMinFlightTimes(tickets);

        assertThat(minFlightTimes).containsKeys("TK", "S7");
        assertThat(minFlightTimes.get("TK")).isEqualTo(350);
        assertThat(minFlightTimes.get("S7")).isEqualTo(605);
    }

    @Test
    public void testCalculateAveragePrice() {
        var averagePrice = calculator.calculateAveragePrice(tickets);
        assertThat(averagePrice).isEqualTo((12400 + 17400) / 2.0);
    }

    @Test
    public void testCalculateMedianPrice() {
        var medianPrice = calculator.calculateMedianPrice(tickets);
        assertThat(medianPrice).isEqualTo((12400 + 17400) / 2.0);
    }

    @Test
    public void testCalculatePriceDifference() {
        var priceDifference = calculator.calculatePriceDifference(tickets);
        var averagePrice = calculator.calculateAveragePrice(tickets);
        var medianPrice = calculator.calculateMedianPrice(tickets);

        assertThat(priceDifference).isEqualTo(averagePrice - medianPrice);
    }
}
