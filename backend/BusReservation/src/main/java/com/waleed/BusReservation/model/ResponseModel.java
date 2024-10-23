package com.waleed.BusReservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
public class ResponseModel<T> {
    private int statusCode;
    private String message;
    private T response;
}
