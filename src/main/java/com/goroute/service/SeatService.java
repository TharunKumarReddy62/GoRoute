package com.goroute.service;

import com.goroute.model.Seat;

import java.util.List;

public interface SeatService {

    List<Seat> getSeatsBySchedule(Long scheduleId);

    List<Seat> getAvailableSeats(Long scheduleId);

    Seat getSeat(Long scheduleId, String seatNumber);
}