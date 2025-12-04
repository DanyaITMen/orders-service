package com.automarket;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

// 👇 Імпорти для RabbitMQ
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderRepository repository;

    // 👇 Канал для відправки повідомлень
    @Inject
    @Channel("invoices-out")
    Emitter<Invoice> invoiceEmitter;

    @GET
    public List<Order> getAll() {
        return repository.listAll();
    }

    @POST
    @Transactional
    public void create(Order order) {
        // 1. Зберігаємо в базу (як і раніше)
        repository.persist(order);

        // 2. 👇 Створюємо і відправляємо повідомлення в RabbitMQ
        Invoice invoice = new Invoice(order.totalPrice, order.car, order.customerName);
        invoiceEmitter.send(invoice);

        System.out.println("📨 [Orders] Відправлено рахунок в чергу!");
    }

    // ... методи update і delete залиш як були
}