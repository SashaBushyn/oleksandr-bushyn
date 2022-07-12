package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanA;
import com.epam.spring.homework2.beans.BeanE;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondAppConfig {

    @Bean
    public BeanA beanA() {
        return new BeanA();
    }

    @Bean
    public BeanE beanE() {
        return new BeanE();
    }
}
