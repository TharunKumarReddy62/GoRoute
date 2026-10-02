package com.goroute.repository;

import com.goroute.model.Booking;
import com.goroute.model.BookingStatus;
import com.goroute.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByUserAndStatus(
            User user,
            BookingStatus status
    );

    boolean existsBySeatIdAndStatus(
            Long seatId,
            BookingStatus status
    );
}