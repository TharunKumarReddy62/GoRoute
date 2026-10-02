package com.goroute.repository;

import com.goroute.model.Seat;
import com.goroute.model.SeatStatus;
import com.goroute.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findBySchedule(Schedule schedule);

    List<Seat> findByScheduleAndStatus(
            Schedule schedule,
            SeatStatus status
    );

    Optional<Seat> findByScheduleAndSeatNumber(
            Schedule schedule,
            String seatNumber
    );
}