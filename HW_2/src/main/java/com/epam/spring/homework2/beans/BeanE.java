package com.epam.spring.homework2.beans;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanE extends BeanParent {

    @PostConstruct
    public void myBeanMethodPostConstruct() {
        System.out.println("calling @PostConstruct method in BeanE");
    }

    @PreDestroy
    public void myPreDestroyMethod() {
        System.out.println("calling @PreDestroy method in BeanE");
    }
}
