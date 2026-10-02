package com.goroute.repository;

import com.goroute.model.Payment;
import com.goroute.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBookingId(Long bookingId);

    boolean existsByBookingIdAndStatus(
            Long bookingId,
            PaymentStatus status
    );
}