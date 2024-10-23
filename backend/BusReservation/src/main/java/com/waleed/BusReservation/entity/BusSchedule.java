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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;

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

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "bus_id")
  private Bus bus;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "route_id")
  private BusRoute busRoute;

  private String departureTime;
  private Integer ticketPrice;
  private Integer discount;
  private Integer processingFee;
}
