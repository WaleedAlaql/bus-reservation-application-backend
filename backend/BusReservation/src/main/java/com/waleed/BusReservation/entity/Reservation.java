package com.waleed.BusReservation.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reservationId;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "bus_schedule_id")
  private BusSchedule busSchedule;

  private Integer timestamp;

  private String departureDate;

  private Integer totalSeatBooked;

  private String seatNumbers;

  private String reservationStatus;

  private Integer totalPrice;
}
