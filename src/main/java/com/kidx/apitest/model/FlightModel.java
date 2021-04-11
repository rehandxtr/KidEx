package com.kidx.apitest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "flight")
public class FlightModel {
    @Id
    String id;
    String flightNumber;
    int totalSeats;
    int seatsLeft;
    List<PassengerModel> passengers;
}
