package com.epam.spring.homework2.beans;

public class BeanC extends BeanParent {


    public BeanC() {
    }

    public BeanC(String name, int value) {
        super(name, value);
    }

    public void myInitNetod() {
        System.out.println("Init method of " + this.toString());
    }

    public void myDestroyMethod() {
        System.out.println("Destroy method of " + this.toString());
    }
}
