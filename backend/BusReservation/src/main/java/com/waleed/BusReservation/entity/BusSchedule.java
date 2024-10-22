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
@Entity
@Table(name = "bus_schedule")
public class BusSchedule {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long scheduleId;
  private Bus bus;
  private BusRoute busRoute;
  private String departureTime;
  private Integer ticketPrice;
  private Integer discount;
  private Integer processingFee;
}
