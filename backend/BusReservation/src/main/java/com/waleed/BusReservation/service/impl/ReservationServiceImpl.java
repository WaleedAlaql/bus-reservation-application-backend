package com.waleed.BusReservation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.waleed.BusReservation.entity.Reservation;
import com.waleed.BusReservation.entity.BusSchedule;
import com.waleed.BusReservation.entity.Customer;
import com.waleed.BusReservation.model.ReservationApiException;
import com.waleed.BusReservation.repository.BusScheduleRepository;
import com.waleed.BusReservation.repository.CustomerRepository;
import com.waleed.BusReservation.repository.ReservationRepository;
import com.waleed.BusReservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BusScheduleRepository busScheduleRepository;

    @Override
    public Reservation addReservation(Reservation busReservation) {
        final Customer customer;
        final boolean doesCustomerExist = customerRepository.existsByMobileOrEmail(busReservation.getCustomer().getMobile(),
                busReservation.getCustomer().getEmail());
        if (doesCustomerExist) {
            customer = customerRepository.findByMobileOrEmail(busReservation.getCustomer().getMobile(),
                    busReservation.getCustomer().getEmail()).orElseThrow();
        } else {
            customer = customerRepository.save(busReservation.getCustomer());
        }
        busReservation.setCustomer(customer);
        return reservationRepository.save(busReservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByBusScheduleAndDepartureDate(Long busScheduleId, String departureDate) {
        final BusSchedule bus_schedule = busScheduleRepository.findById(busScheduleId).orElseThrow(
                () -> new ReservationApiException(HttpStatus.NOT_FOUND, "Bus schedule not found"));
        return reservationRepository.findByBusScheduleAndDepartureDate(bus_schedule, departureDate)
                .orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "No reservations found"));
    }

    @Override
    public List<Reservation> getReservationsByMobile(String mobile) {
        final Customer customer = customerRepository.findByMobile(mobile).orElseThrow(
                () -> new ReservationApiException(HttpStatus.NOT_FOUND, "Customer not found"));
        return reservationRepository.findByCustomer(customer)
                .orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "No reservations found"));
    }
}
