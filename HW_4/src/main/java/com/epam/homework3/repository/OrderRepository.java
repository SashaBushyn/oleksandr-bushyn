package com.epam.homework3.repository;

import com.epam.homework3.model.entity.Order;
import com.epam.homework3.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUserId(Long userid);
}
