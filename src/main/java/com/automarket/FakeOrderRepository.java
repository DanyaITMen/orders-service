package com.automarket;

import com.automarket.grpc.OrderStatusResponse;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class FakeOrderRepository {
    // Імітація таблиці в базі даних: ID -> Об'єкт замовлення
    private static final Map<Integer, OrderStatusResponse> ORDERS = Map.of(
            1, OrderStatusResponse.newBuilder().setOrderId(1).setProductName("KIA SOUL 2014").setStatus("PAID").build(),
            2, OrderStatusResponse.newBuilder().setOrderId(2).setProductName("Charge").setStatus("PENDING_PAYMENT").build(),
            3, OrderStatusResponse.newBuilder().setOrderId(3).setProductName("NISSAN LEAF 2014").setStatus("PAID").build()
    );

    public Optional<OrderStatusResponse> findById(int orderId) {
        return Optional.ofNullable(ORDERS.get(orderId));
    }
}