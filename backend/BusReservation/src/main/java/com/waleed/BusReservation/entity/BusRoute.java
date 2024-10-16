package com.waleed.BusReservation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusRoute {

  private Long routeId;
  private String routeName;
  private String cityFrom;
  private String cityTo;
  private double distanceInKm;
}
