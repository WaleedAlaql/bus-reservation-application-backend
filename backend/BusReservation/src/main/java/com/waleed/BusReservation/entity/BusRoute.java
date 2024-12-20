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
@Table(name = "bus_route")
public class BusRoute {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long routeId;
  private String routeName;
  private String cityFrom;
  private String cityTo;
  private double distanceInKm;
}
