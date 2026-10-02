package com.goroute.service;

import com.goroute.model.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    Schedule createSchedule(Schedule schedule);

    Schedule getScheduleById(Long id);

    List<Schedule> getSchedules(
            Long routeId,
            LocalDate journeyDate
    );
}