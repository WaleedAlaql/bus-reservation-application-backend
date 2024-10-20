package com.waleed.BusReservation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.waleed.BusReservation.entity.Bus;
import com.waleed.BusReservation.repository.BusRepository;
import com.waleed.BusReservation.service.BusService;

@Service
public class BusServiceImpl implements BusService{

    @Autowired
    private BusRepository busRepository;

    @Override
    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public List<Bus> getAllBus() {
        return busRepository.findAll();
    }
    
}
