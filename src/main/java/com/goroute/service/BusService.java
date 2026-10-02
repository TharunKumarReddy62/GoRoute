package com.goroute.service;

import com.goroute.model.Bus;

import java.util.List;

public interface BusService {

    Bus createBus(Bus bus);

    Bus getBusById(Long id);

    List<Bus> getAllBuses();
}