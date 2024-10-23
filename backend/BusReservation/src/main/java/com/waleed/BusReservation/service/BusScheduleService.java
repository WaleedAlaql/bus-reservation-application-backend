package com.waleed.BusReservation.service;

import java.util.List;
import com.waleed.BusReservation.entity.BusSchedule;

public interface BusScheduleService {

    BusSchedule addBusSchedule(BusSchedule busSchedule);

    List<BusSchedule> getAllBusSchedule();

    List<BusSchedule> getBusScheduleByBusRoute(String busRoute);
}
