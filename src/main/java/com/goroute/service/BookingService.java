package com.goroute.service;

import com.goroute.model.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(
            Long userId,
            Long scheduleId,
            Long seatId
    );

    Booking getBookingById(Long id);

    List<Booking> getUserBookings(Long userId);

    void cancelBooking(Long bookingId, Long userId);
}