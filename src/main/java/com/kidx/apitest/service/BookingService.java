package com.kidx.apitest.service;

import com.kidx.apitest.model.BookingRequest;
import com.kidx.apitest.model.FlightModel;
import com.kidx.apitest.model.FlightResponseModel;
import com.kidx.apitest.model.PassengerModel;
import com.kidx.apitest.repository.FlightRepository;
import com.kidx.apitest.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    FlightRepository flightRepository;

    public FlightResponseModel bookFlight(BookingRequest bookingRequest) {
        FlightModel f = flightRepository.findByFlightNumber(bookingRequest.getFlightNumber());
        if (f == null) {
            return new FlightResponseModel(Constants.NOT_EXISTS,Constants.FAILURE,null);
        } else if (f.getSeatsLeft() == 0) {
            return new FlightResponseModel(Constants.TICKET_FULL,Constants.FAILURE,null);
        } else {
            PassengerModel passenger = new PassengerModel();
            passenger.setName(bookingRequest.getUserName());
            if(f.getTotalSeats() - f.getSeatsLeft() == 0) {
                List<PassengerModel> p = new ArrayList<>();
                passenger.setSeatNumber(1);
                p.add(passenger);
                f.setPassengers(p);
            } else {
                passenger.setSeatNumber(f.getPassengers().size() + 1);
                f.getPassengers().add(passenger);
            }
            f.setSeatsLeft(f.getSeatsLeft() - 1);
            flightRepository.save(f);
            return new FlightResponseModel(Constants.SUCCESS,Constants.SUCCESS,passenger.getSeatNumber());
        }
    }
}
