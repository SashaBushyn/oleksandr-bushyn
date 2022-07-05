package com.epam.spring.homework2.beans;

public class BeanD extends BeanParent {

    public BeanD(String name, int value) {
        super(name, value);
    }

    public void myInitMethod() {
        System.out.println("Init method of " + this.getClass().getSimpleName());
    }

    public void myDestroyMethod() {
        System.out.println("Destroy method of " + this.getClass().getSimpleName());
    }
}
