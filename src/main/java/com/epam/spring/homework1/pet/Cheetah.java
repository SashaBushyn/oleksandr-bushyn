package com.epam.spring.homework1.pet;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Cheetah implements Animal {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
