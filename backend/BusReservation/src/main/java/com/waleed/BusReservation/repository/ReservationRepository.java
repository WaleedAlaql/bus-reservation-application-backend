package com.waleed.BusReservation.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.waleed.BusReservation.entity.Reservation;
import com.waleed.BusReservation.entity.Customer;
import com.waleed.BusReservation.entity.BusSchedule;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<List<Reservation>> findByCustomer(Customer customer);

    Optional<List<Reservation>> findByBusScheduleAndDepartureDate(BusSchedule busSchedule, String departureDate);
}
