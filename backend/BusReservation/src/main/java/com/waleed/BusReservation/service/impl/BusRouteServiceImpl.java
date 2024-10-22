package com.waleed.BusReservation.service.impl;

import com.waleed.BusReservation.entity.BusRoute;
import com.waleed.BusReservation.model.ReservationApiException;
import com.waleed.BusReservation.repository.BusRouteRepository;
import com.waleed.BusReservation.service.BusRouteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    @Autowired
    private BusRouteRepository busRouteRepository;

    @Override
    public BusRoute addBusRoute(BusRoute busRoute) {
        return busRouteRepository.save(busRoute);
    }

    @Override
    public List<BusRoute> getAllBusRoute() {
        return busRouteRepository.findAll();
    }

    @Override
    public BusRoute getBusRouteByRouteName(String routeName) {
        return busRouteRepository.findByRouteName(routeName).orElseThrow(() -> new ReservationApiException( HttpStatus.NOT_FOUND, "Bus Route not found with route name: " + routeName));
    }

    @Override
    public BusRoute getBusRouteByCityFromAndCityTo(String cityFrom, String cityTo) {
        return busRouteRepository.findByCityFromAndCityTo(cityFrom, cityTo).orElseThrow(() -> new ReservationApiException( HttpStatus.NOT_FOUND, "Bus Route not found with city from: " + cityFrom + " and city to: " + cityTo));
    }
}
