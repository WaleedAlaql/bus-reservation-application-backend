package com.waleed.BusReservation.controller;

import com.waleed.BusReservation.entity.BusRoute;
import com.waleed.BusReservation.service.BusRouteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
    
@RestController
@RequestMapping("/api/bus-route")
public class BusRouteController {

    @Autowired
    private BusRouteService busRouteService;

    @PostMapping("/add")
    public ResponseEntity<BusRoute> addBusRoute(@RequestBody BusRoute busRoute) {
        return ResponseEntity.ok(busRouteService.addBusRoute(busRoute));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BusRoute>> getAllBusRoute() {
        return ResponseEntity.ok(busRouteService.getAllBusRoute());
    }

    @GetMapping("/route-name/{routeName}")
    public ResponseEntity<BusRoute> getBusRouteByRouteName(@PathVariable String routeName) {
        return ResponseEntity.ok(busRouteService.getBusRouteByRouteName(routeName));
    }

    @GetMapping("/city-from-to/{cityFrom}/{cityTo}")
    public ResponseEntity<BusRoute> getBusRouteByCityFromAndCityTo(@PathVariable String cityFrom, @PathVariable String cityTo) {
        return ResponseEntity.ok(busRouteService.getBusRouteByCityFromAndCityTo(cityFrom, cityTo));
    }
}
