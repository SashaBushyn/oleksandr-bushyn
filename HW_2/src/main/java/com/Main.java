package com;

import com.epam.spring.homework2.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext apx = new AnnotationConfigApplicationContext(AppConfig.class);

        apx.close();
        for (String name : apx.getBeanDefinitionNames()) {
           System.out.println(apx.getBeanDefinition(name));
        }
    }
}
