package com.mentoring.palmetto_service.messaging;

import com.mentoring.palmetto_service.DTO.PizzaOrderDTO;
import com.mentoring.palmetto_service.service.PizzaCookingService;
import com.mentoring.palmetto_service.utils.JsonConvertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PizzaOrderConsumer {
    private final PizzaCookingService pizzaCookingService;
    private final JsonConvertor jsonConvertor;

    @KafkaListener(topics = "${topics.consuming.fetch-pizza-orders}")
    public void onPizzaOrderCreate(@Payload List<String> pizzaOrdersJson, Acknowledgment acks) {
        log.info("Receiving pizza orders to cooking. Orders: {}", pizzaOrdersJson);

        String ordersJson = pizzaOrdersJson.stream()
                .collect(Collectors.joining(",", "[", "]"));

        List<PizzaOrderDTO> pizzaOrders = jsonConvertor.deserializeListItems(ordersJson, PizzaOrderDTO.class);

        pizzaOrders.forEach(pizzaCookingService::cookPizza);
        acks.acknowledge();
    }
}


