package io.code.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Ticket {
    @JsonProperty("origin")
    public String origin;

    @JsonProperty("origin_name")
    public String originName;

    @JsonProperty("destination")
    public String destination;

    @JsonProperty("destination_name")
    public String destinationName;

    @JsonProperty("departure_date")
    public String departureDate;

    @JsonProperty("departure_time")
    public String departureTime;

    @JsonProperty("arrival_date")
    public String arrivalDate;

    @JsonProperty("arrival_time")
    public String arrivalTime;

    @JsonProperty("carrier")
    public String carrier;

    @JsonProperty("stops")
    public int stops;

    @JsonProperty("price")
    public int price;
}
