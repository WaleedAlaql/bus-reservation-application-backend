package com.waleed.BusReservation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.waleed.BusReservation.service.BusScheduleService;
import com.waleed.BusReservation.entity.BusSchedule;
import com.waleed.BusReservation.model.ReservationApiException;
import com.waleed.BusReservation.repository.BusRouteRepository;
import com.waleed.BusReservation.repository.BusScheduleRepository;
import com.waleed.BusReservation.entity.Bus;
import com.waleed.BusReservation.entity.BusRoute;
import com.waleed.BusReservation.repository.BusRepository;

@Service
public class BusScheduleServiceImpl implements BusScheduleService {
    
    @Autowired
    private BusScheduleRepository busScheduleRepository;

    @Autowired
    private BusRouteRepository busRouteRepository;

    @Autowired
    private BusRepository busRepository;

    @Override
    public BusSchedule addBusSchedule(BusSchedule busSchedule) throws ReservationApiException {
        final Boolean exists = busScheduleRepository.existsByBusAndBusRouteAndDepartureTime(
            busSchedule.getBus(), busSchedule.getBusRoute(), busSchedule.getDepartureTime());
        if (exists) {
            throw new ReservationApiException(HttpStatus.CONFLICT, "Bus schedule already exists");
        }
        
        // Ensure that Bus and BusRoute are attached to the current session
        Bus bus = busRepository.findById(busSchedule.getBus().getBusId())
            .orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "Bus not found"));
        BusRoute busRoute = busRouteRepository.findById(busSchedule.getBusRoute().getRouteId())
            .orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "Bus route not found"));
        
        busSchedule.setBus(bus);
        busSchedule.setBusRoute(busRoute);
        
        return busScheduleRepository.save(busSchedule);
    }

    @Override
    public List<BusSchedule> getAllBusSchedule() {
        return busScheduleRepository.findAll();
    }

    @Override
    public List<BusSchedule> getBusScheduleByBusRoute(String routeName) {
        final BusRoute busRoute = busRouteRepository.findByRouteName(routeName).orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "Bus route not found"));
        return busScheduleRepository.findByBusRoute(busRoute).orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "Bus schedule not found"));
    }
}
