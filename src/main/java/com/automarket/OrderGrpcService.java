package com.automarket;

import com.automarket.grpc.OrderStatusRequest;
import com.automarket.grpc.OrderStatusResponse;
import com.automarket.grpc.OrderServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import jakarta.inject.Inject;

@GrpcService
public class OrderGrpcService extends OrderServiceGrpc.OrderServiceImplBase {

    @Inject
    FakeOrderRepository repository; // Інжектуємо наш фейковий репозиторій

    @Override
    public void getOrderStatus(OrderStatusRequest request, StreamObserver<OrderStatusResponse> responseObserver) {
        int orderId = request.getOrderId();

        // Шукаємо замовлення в репозиторії
        repository.findById(orderId)
                .ifPresentOrElse(
                        order -> {
                            // Якщо знайшли, відправляємо відповідь
                            responseObserver.onNext(order);
                            responseObserver.onCompleted();
                        },
                        () -> {
                            // Якщо не знайшли, відправляємо помилку NOT_FOUND
                            responseObserver.onError(Status.NOT_FOUND
                                    .withDescription("Order with ID " + orderId + " not found.")
                                    .asRuntimeException());
                        }
                );
    }
}