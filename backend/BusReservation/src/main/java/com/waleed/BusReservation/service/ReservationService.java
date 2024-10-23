package com.waleed.BusReservation.service;

import java.util.List;

import com.waleed.BusReservation.entity.Reservation;

public interface ReservationService {

    Reservation addReservation(Reservation busReservation);

    List<Reservation> getAllReservations();

    List<Reservation> getReservationsByBusScheduleAndDepartureDate(Long busScheduleId, String departureDate);

    List<Reservation> getReservationsByMobile(String mobile);
}
