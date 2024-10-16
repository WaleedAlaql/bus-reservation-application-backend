package com.waleed.BusReservation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bus {
   
    private Long busId;
    private String busName;
    private String busType;
    private Integer totalSeats;
    private String busNumber;
}