package com.scalar.bookmyshow24.Repository;

import com.scalar.bookmyshow24.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Override
    Booking save(Booking booking);
}
