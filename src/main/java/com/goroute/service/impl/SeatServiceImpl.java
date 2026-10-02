package com.goroute.service.impl;

import com.goroute.model.Schedule;
import com.goroute.model.Seat;
import com.goroute.model.SeatStatus;
import com.goroute.repository.SeatRepository;
import com.goroute.service.ScheduleService;
import com.goroute.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final ScheduleService scheduleService;

    public SeatServiceImpl(
            SeatRepository seatRepository,
            ScheduleService scheduleService) {

        this.seatRepository = seatRepository;
        this.scheduleService = scheduleService;
    }

    @Override
    public List<Seat> getSeatsBySchedule(Long scheduleId) {

        Schedule schedule =
                scheduleService.getScheduleById(scheduleId);

        return seatRepository.findBySchedule(schedule);
    }

    @Override
    public List<Seat> getAvailableSeats(Long scheduleId) {

        Schedule schedule =
                scheduleService.getScheduleById(scheduleId);

        return seatRepository.findByScheduleAndStatus(
                schedule,
                SeatStatus.AVAILABLE
        );
    }

    @Override
    public Seat getSeat(Long scheduleId, String seatNumber) {

        Schedule schedule =
                scheduleService.getScheduleById(scheduleId);

        return seatRepository.findByScheduleAndSeatNumber(
                schedule,
                seatNumber
        ).orElseThrow(() ->
                new RuntimeException("Seat not found"));
    }
}