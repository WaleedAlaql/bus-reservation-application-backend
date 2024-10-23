package com.waleed.BusReservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.waleed.BusReservation.entity.Reservation;
import com.waleed.BusReservation.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    

    @PostMapping("/add")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.addReservation(reservation));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/bus-schedule/{busScheduleId}/departure-date/{departureDate}")
    public ResponseEntity<List<Reservation>> getReservationsByBusScheduleAndDepartureDate(@PathVariable Long busScheduleId,
            @PathVariable String departureDate) {
        return ResponseEntity.ok(reservationService.getReservationsByBusScheduleAndDepartureDate(busScheduleId,
                departureDate));
    }

    @GetMapping("/mobile/{mobile}")
    public ResponseEntity<List<Reservation>> getReservationsByMobile(@PathVariable String mobile) {
        return ResponseEntity.ok(reservationService.getReservationsByMobile(mobile));
    }
}
