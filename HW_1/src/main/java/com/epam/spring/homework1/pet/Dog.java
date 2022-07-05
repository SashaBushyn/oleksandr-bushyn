package com.epam.spring.homework1.pet;

import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(PriorityOrdered.HIGHEST_PRECEDENCE)
public class Dog implements Animal {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
