package com.automarket;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped // Це робить його доступним для Injection
public class OrderRepository implements PanacheRepository<Order> {

    // Тут можна писати свої методи пошуку, наприклад:
    public List<Order> findByCustomer(String name) {
        return list("customerName", name);
    }

    // Базові методи (persist, delete, listAll) вже є всередині PanacheRepository!
}