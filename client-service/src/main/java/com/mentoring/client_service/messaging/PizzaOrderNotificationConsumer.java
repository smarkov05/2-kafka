package com.mentoring.client_service.messaging;

import com.mentoring.client_service.model.PizzaOrder;
import com.mentoring.client_service.service.PizzaOrderService;
import com.mentoring.client_service.utils.JsonConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class PizzaOrderNotificationConsumer {
    private final PizzaOrderService orderService;
    private final JsonConvertor jsonConvertor;

    @KafkaListener(topics = "${topics.consuming.fetch-pizza-order-notifications}")
    public void onUpdatePizzaOrderStatus(@Payload List<String> pizzaOrdersJson, Acknowledgment acks) {
        Objects.requireNonNull(pizzaOrdersJson);
        String ordersJson = pizzaOrdersJson.stream()
                .collect(Collectors.joining(",", "[", "]"));

        List<PizzaOrder> pizzaOrders = jsonConvertor.deserializeListItems(ordersJson, PizzaOrder.class);
        pizzaOrders.forEach(orderService::updatePizzaOrder);
        acks.acknowledge();
    }
}
