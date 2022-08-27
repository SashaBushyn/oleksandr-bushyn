package com.epam.homework3.repository;

import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.model.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Boolean existsByRoomNumber(Integer roomNumber);

    @Query(nativeQuery = true,
            value = "select r.* from room r where(select distinct room_id from " +
                    "hotel.reserved_rooms where blocked = 0 and  DATE(date_of_reserve) " +
                    "BETWEEN DATE (:dateIn) AND DATE(:dateOut))")
    Page<Room> getFreeRoomsOnDates(@Param("dateIn") LocalDate dateIn, @Param("dateOut") LocalDate dateOut, Pageable pageable);

    @Query(nativeQuery = true,
            value = "select room.*, orders.room_class, orders.persons_number, orders.date_in, " +
                    "orders.date_out from room, orders where blocked = 0 and room.id not in " +
                    "(select distinct room_id from reserved_rooms where date(date_of_reserve)" +
                    " BETWEEN  date(orders.date_in) and date(orders.date_out)) and orders.id = :id " +
                    "and room.room_class = orders.room_class" +
                    " and room.persons_number = orders.persons_number")
    List<Room> getFreeRoomsForOrder(@Param("id") Long id);
}
