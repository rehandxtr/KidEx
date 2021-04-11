package com.kidx.apitest.controller;

import com.kidx.apitest.utils.Constants;
import com.kidx.apitest.model.FlightRequestModel;
import com.kidx.apitest.model.FlightResponseModel;
import com.kidx.apitest.service.FlightService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;
    private static final Logger logger = Logger.getLogger(FlightController.class);

    @PostMapping("/scheduleFlight")
    public ResponseEntity<?> scheduleFlight(@RequestBody FlightRequestModel flightRequestModel){
       FlightResponseModel f = flightService.scheduleFlight(flightRequestModel);
       if(f == null) {
           logger.error("error in storing data");
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       } else {
           if(f.getStatus().equals(Constants.FAILURE)) {
               logger.info("Flight already exists");
               return new ResponseEntity<>(f, HttpStatus.FAILED_DEPENDENCY);
           } else {
               logger.info("flight saved");
               return new ResponseEntity<>(f, HttpStatus.OK);
           }
       }
    }

    @GetMapping("/getAvailableSeats")
    public ResponseEntity<?> getAvailableSeats(@RequestParam String flightNumber) {
        FlightResponseModel f = flightService.findSeats(flightNumber);
        if(f.getStatus().equals(Constants.SUCCESS)) {
            return new ResponseEntity<>(f,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(f,HttpStatus.NOT_FOUND);
        }
    }
}
