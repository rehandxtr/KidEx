package com.kidx.apitest.service;

import com.kidx.apitest.utils.Constants;
import com.kidx.apitest.repository.FlightRepository;
import com.kidx.apitest.model.FlightModel;
import com.kidx.apitest.model.FlightRequestModel;
import com.kidx.apitest.model.FlightResponseModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;
    private static final Logger logger = Logger.getLogger(FlightService.class);


    public FlightResponseModel scheduleFlight(FlightRequestModel flightRequestModel) {
        try {
            if(flightRepository.findByFlightNumber(flightRequestModel.getFlightNumber()) == null) {
                FlightModel flightModel = new FlightModel();
                flightModel.setFlightNumber(flightRequestModel.getFlightNumber());
                flightModel.setTotalSeats(flightRequestModel.getNumberOfSeats());
                flightModel.setSeatsLeft(flightRequestModel.getNumberOfSeats());
                flightModel.setPassengers(null);
                flightRepository.save(flightModel);
                logger.info("Flight saved");
                return new FlightResponseModel(Constants.SUCCESS,Constants.SUCCESS,flightRequestModel.getNumberOfSeats());
            } else {
                return new FlightResponseModel(Constants.EXISTS,Constants.FAILURE,null);
            }

        } catch (Exception e) {
            logger.error("Exception in saving flight",e.getCause());
        }
        return null;
    }

    public FlightResponseModel findSeats(String flightNumber) {
        try {
            FlightModel f = flightRepository.findByFlightNumber(flightNumber);
            if (f != null) {
                logger.info("Flight found");
                return new FlightResponseModel("", Constants.SUCCESS, f.getSeatsLeft());
            } else {
                logger.info("Flight not found");
                return new FlightResponseModel("", Constants.FAILURE, null);
            }
        } catch (Exception e){
            logger.error("Exception in finding flight",e.getCause());
        }
        return null;
    }
}
