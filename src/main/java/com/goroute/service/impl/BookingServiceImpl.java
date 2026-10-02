package com.goroute.service.impl;

import com.goroute.model.*;
import com.goroute.repository.BookingRepository;
import com.goroute.repository.SeatRepository;
import com.goroute.repository.UserRepository;
import com.goroute.service.BookingService;
import com.goroute.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final ScheduleService scheduleService;

    public BookingServiceImpl(
            BookingRepository bookingRepository,
            UserRepository userRepository,
            SeatRepository seatRepository,
            ScheduleService scheduleService) {

        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.seatRepository = seatRepository;
        this.scheduleService = scheduleService;
    }

    @Override
    @Transactional
    public Booking createBooking(
            Long userId,
            Long scheduleId,
            Long seatId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Schedule schedule =
                scheduleService.getScheduleById(scheduleId);

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() ->
                        new RuntimeException("Seat not found"));

        if (!seat.getSchedule().getId().equals(scheduleId)) {
            throw new RuntimeException(
                    "Seat does not belong to this schedule");
        }

        if (seat.getStatus() != SeatStatus.AVAILABLE) {
            throw new RuntimeException("Seat is not available");
        }

        seat.setStatus(SeatStatus.BOOKED);
        seatRepository.save(seat);

        Booking booking = Booking.builder()
                .user(user)
                .schedule(schedule)
                .seat(seat)
                .amount(schedule.getFare())
                .status(BookingStatus.CONFIRMED)
                .bookedAt(LocalDateTime.now())
                .build();

        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) {

        return bookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));
    }

    @Override
    public List<Booking> getUserBookings(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return bookingRepository.findByUser(user);
    }

    @Override
    @Transactional
    public void cancelBooking(Long bookingId, Long userId) {

        Booking booking = getBookingById(bookingId);

        if (!booking.getUser().getId().equals(userId)) {
            throw new RuntimeException(
                    "You cannot cancel this booking");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException(
                    "Booking is already cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);

        Seat seat = booking.getSeat();
        seat.setStatus(SeatStatus.AVAILABLE);

        seatRepository.save(seat);
        bookingRepository.save(booking);
    }
}