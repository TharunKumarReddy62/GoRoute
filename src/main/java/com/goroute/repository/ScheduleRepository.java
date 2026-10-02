package com.goroute.repository;

import com.goroute.model.Schedule;
import com.goroute.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByRouteAndJourneyDate(
            Route route,
            LocalDate journeyDate
    );
}