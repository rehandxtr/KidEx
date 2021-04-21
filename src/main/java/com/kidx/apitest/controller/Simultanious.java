package com.kidx.apitest.controller;

import com.kidx.apitest.model.BookingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Simultanious {
    BookingController bookingController = new BookingController();
    @PostMapping("/simultanious")
    public ResponseEntity<?> bookSeat(@RequestBody BookingRequest bookingRequest) {
        BookingRequest b2 = new BookingRequest();
        b2.setUserName("new username");
        b2.setFlightNumber(bookingRequest.getFlightNumber());
        new Thread(() -> bookingController.bookSeat(bookingRequest)).start();
        new Thread(() -> bookingController.bookSeat(b2)).start();
        new Thread(() -> bookingController.bookSeat(bookingRequest)).start();
        new Thread(() -> bookingController.bookSeat(bookingRequest)).start();
        return new ResponseEntity<>("saved", HttpStatus.OK);
    }
}
