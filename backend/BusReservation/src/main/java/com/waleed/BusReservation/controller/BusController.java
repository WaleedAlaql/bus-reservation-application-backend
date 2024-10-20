package com.waleed.BusReservation.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.waleed.BusReservation.entity.Bus;
import com.waleed.BusReservation.service.BusService;

@RestController
@RequestMapping("/api/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping("/add")
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus){
        return ResponseEntity.ok(busService.addBus(bus));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Bus>> getAllBus(){
        return ResponseEntity.ok(busService.getAllBus());
    }
}
