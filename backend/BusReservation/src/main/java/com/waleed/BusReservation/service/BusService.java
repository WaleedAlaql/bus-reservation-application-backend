package com.waleed.BusReservation.service;

import java.util.List;
import com.waleed.BusReservation.entity.Bus;

public interface BusService {
    Bus addBus(Bus bus);
    List<Bus> getAllBus();
}
