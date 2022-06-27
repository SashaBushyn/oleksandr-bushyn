package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.epam.spring.homework2")
@PropertySource("application.properties")
@Import(SecondAppConfig.class)

public class AppConfig {

    @Bean(initMethod = "myInitNetod", destroyMethod = "myDestroyMethod")
    @DependsOn("beanD")
    public BeanB beanB(@Value("${beanB.name}") String name, @Value("${beanB.value}") int value) {
        return new BeanB(name,value);
    }


    @Bean(initMethod = "myInitNetod", destroyMethod = "myDestroyMethod")
    @DependsOn("beanB")
    public BeanC beanC(@Value("${beanC.name}") String name, @Value("${beanC.value}") int value) {
        return new BeanC(name, value);
    }


    @Bean(initMethod = "myInitNetod", destroyMethod = "myDestroyMethod")
    public BeanD beanD(@Value("${beanD.name}") String name, @Value("${beanD.value}") int value) {
        return new BeanD(name, value);
    }

}
