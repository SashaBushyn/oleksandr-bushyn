package com.epam.spring.homework2.beans;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class BeanE extends BeanParent {

    public BeanE(String name, int value) {
        super(name, value);
    }

    public BeanE() {
    }

    @PostConstruct
    public void myBeanMethodPostConstruct (){
        System.out.println("calling @PostConstruct method in BeanE" );
    }
    @PreDestroy
    public void myPreDestroyMethod(){
        System.out.println("calling @PreDestroy method in BeanE" );
    }
}
