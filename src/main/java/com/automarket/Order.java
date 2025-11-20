package com.automarket;

import jakarta.persistence.*;

@Entity
@Table(name = "orders") // "order" - зарезервоване слово в SQL, тому краще "orders"
public class Order {

    @Id
    @GeneratedValue // Автоматичний ID
    public Long id;

    public String car;
    public String customerName;
    public String status; // Наприклад: "Pending", "Paid"
    public int days;
    public double totalPrice;

    // Порожній конструктор обов'язковий
    public Order() {}

    public Order(String car, String customerName, int days, double totalPrice) {
        this.car = car;
        this.customerName = customerName;
        this.days = days;
        this.totalPrice = totalPrice;
        this.status = "Pending";
    }
}