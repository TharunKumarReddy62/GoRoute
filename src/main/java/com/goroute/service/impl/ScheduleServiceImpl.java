package com.goroute.service.impl;

import com.goroute.model.Route;
import com.goroute.model.Schedule;
import com.goroute.repository.ScheduleRepository;
import com.goroute.service.RouteService;
import com.goroute.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final RouteService routeService;

    public ScheduleServiceImpl(
            ScheduleRepository scheduleRepository,
            RouteService routeService) {

        this.scheduleRepository = scheduleRepository;
        this.routeService = routeService;
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Schedule not found"));
    }

    @Override
    public List<Schedule> getSchedules(
            Long routeId,
            LocalDate journeyDate) {

        Route route = routeService.getRouteById(routeId);

        return scheduleRepository.findByRouteAndJourneyDate(
                route,
                journeyDate
        );
    }
}