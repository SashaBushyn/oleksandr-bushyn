package com.epam.homework3.repository;

import com.epam.homework3.model.entity.Booking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookingRepository  extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUserId(Long userId, Pageable pageable);
}
