package com.epam.spring.homework2.beans;

public class BeanB extends BeanParent {

    public BeanB() {
    }

    public BeanB(String name, int value) {
        super(name, value);
    }

    public void myInitNetod() {
        System.out.println("Init method of " + this.toString());
    }

    public void myDestroyMethod() {
        System.out.println("Destroy method of " + this.toString());
    }

    public void NewInitMethod(){
        System.out.println("Changed init method in BeanB ");
    }
}
