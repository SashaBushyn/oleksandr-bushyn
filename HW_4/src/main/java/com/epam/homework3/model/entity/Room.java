package com.epam.homework3.model.entity;

import com.epam.homework3.model.enums.RoomClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private Integer roomNumber;
    @Column(nullable = false)
    private RoomClass roomClass;
    @Column(nullable = false)
    private Integer personsNumber;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private boolean blocked;
}
