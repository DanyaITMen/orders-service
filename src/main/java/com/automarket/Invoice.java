package com.automarket;

public class Invoice {
    public double price;
    public String car;
    public String user;

    public Invoice() {}

    public Invoice(double price, String car, String user) {
        this.price = price;
        this.car = car;
        this.user = user;
    }
}