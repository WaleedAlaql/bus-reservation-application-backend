package com.waleed.BusReservation.model;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class ReservationApiException extends RuntimeException {
   private final HttpStatus httpStatus;
   private final String message;
}
