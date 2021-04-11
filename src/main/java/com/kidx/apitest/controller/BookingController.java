package com.kidx.apitest.controller;

import com.kidx.apitest.model.BookingRequest;
import com.kidx.apitest.model.FlightResponseModel;
import com.kidx.apitest.service.BookingService;
import com.kidx.apitest.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;

    @PostMapping("/bookSeat")
    public ResponseEntity<?> bookSeat(@RequestBody BookingRequest bookingRequest) {
        FlightResponseModel f = bookingService.bookFlight(bookingRequest);
        if(f.getMessage().equals(Constants.NOT_EXISTS)) {
            return new ResponseEntity<>(Constants.NOT_EXISTS, HttpStatus.NOT_FOUND);
        } else {
            if (f.getMessage().equals(Constants.TICKET_FULL)) {
                return new ResponseEntity<>(f,HttpStatus.EXPECTATION_FAILED);
            } else {
                return new ResponseEntity<>(f,HttpStatus.OK);
            }
        }
    }
}
