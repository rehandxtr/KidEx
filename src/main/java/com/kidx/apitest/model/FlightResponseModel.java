package com.kidx.apitest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightResponseModel {
    String message;
    String status;
    Integer count;

}
