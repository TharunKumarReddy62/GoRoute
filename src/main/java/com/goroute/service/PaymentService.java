package com.goroute.service;

import com.goroute.model.Payment;

public interface PaymentService {

    Payment makePayment(Long bookingId);

    Payment getPaymentByBooking(Long bookingId);
}