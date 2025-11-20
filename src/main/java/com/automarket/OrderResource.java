package com.automarket;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// @Authenticated (Поки закоментовано для тесту)
public class OrderResource {

    @Inject
    OrderRepository repository; // <-- Впроваджуємо наш репозиторій

    @GET
    public List<Order> getAll() {
        return repository.listAll(); // Використовуємо репозиторій
    }

    @POST
    @Transactional
    public void create(Order order) {
        repository.persist(order); // Зберігаємо через репозиторій
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        repository.deleteById(id);
    }
}