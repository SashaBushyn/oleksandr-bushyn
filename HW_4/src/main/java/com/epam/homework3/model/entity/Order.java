package com.epam.homework3.model.entity;

import com.epam.homework3.model.enums.OrderHandling;
import com.epam.homework3.model.enums.RoomClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private LocalDate dateCreation;
    private RoomClass roomClass;
    private Integer personsNumber;
    private OrderHandling status;
}
