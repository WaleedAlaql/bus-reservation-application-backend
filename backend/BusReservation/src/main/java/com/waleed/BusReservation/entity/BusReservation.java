package com.waleed.BusReservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @Entity
// @Table(name = "bus_reservation")
public class BusReservation {

  // @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
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
