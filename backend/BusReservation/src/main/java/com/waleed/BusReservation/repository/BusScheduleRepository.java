package com.waleed.BusReservation.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.waleed.BusReservation.entity.BusSchedule;
import com.waleed.BusReservation.entity.Bus;
import com.waleed.BusReservation.entity.BusRoute;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Long> {

    Optional<List<BusSchedule>> findByBusRoute(BusRoute busRoute);

    Boolean existsByBusAndBusRouteAndDepartureTime(Bus bus, BusRoute busRoute, String departureTime);
}
