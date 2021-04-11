package com.kidx.apitest.repository;

import com.kidx.apitest.model.FlightModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightRepository extends MongoRepository<FlightModel,String> {
    FlightModel findByFlightNumber(String number);
}
