package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class BeanA extends BeanParent implements InitializingBean, DisposableBean {

    public BeanA(String name, int value) {
        super(name, value);
    }

    public BeanA() {
    }

    @Override
    public void destroy() {
        System.out.println("BeanA destroy method");
    }

    @Override
    public void afterPropertiesSet() {

        System.out.println("BeanA afterPropertiesSet method");

    }
}
