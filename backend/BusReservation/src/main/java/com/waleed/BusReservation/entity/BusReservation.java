package com.waleed.BusReservation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusReservation {

  private Long reservationId;
  private Customer customer;
  private BusSchedule busSchedule;
  private Integer timestamp;
  private String departureDate;
  private Integer totalSeatBooked;
  private String seatNumbers;
  private String reservationStatus;
  private Integer totalPrice;
}
