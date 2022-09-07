package com.epam.homework3.repository;

import com.epam.homework3.model.entity.ReservedRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedRoomsRepository extends JpaRepository<ReservedRooms, Long> {
}
