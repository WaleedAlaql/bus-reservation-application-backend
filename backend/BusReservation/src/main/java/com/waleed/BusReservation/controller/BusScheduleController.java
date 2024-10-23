package com.waleed.BusReservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import com.waleed.BusReservation.entity.BusSchedule;
import com.waleed.BusReservation.service.BusScheduleService;

@RestController
@RequestMapping("/api/bus-schedule") 
public class BusScheduleController {
    
    @Autowired
    private BusScheduleService busScheduleService;

    @PostMapping("/add")
    public ResponseEntity<BusSchedule> addBusSchedule(@RequestBody BusSchedule busSchedule) {
        return ResponseEntity.ok(busScheduleService.addBusSchedule(busSchedule));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BusSchedule>> getAllBusSchedule() {
        return ResponseEntity.ok(busScheduleService.getAllBusSchedule());
    }

    @GetMapping("/route/{routeName}")
    public ResponseEntity<List<BusSchedule>> getBusScheduleByBusRoute(@PathVariable String routeName) {
        return ResponseEntity.ok(busScheduleService.getBusScheduleByBusRoute(routeName));
    }
}
