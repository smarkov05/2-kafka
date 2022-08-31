package com.mentoring.courier_service.messaging;

import com.mentoring.courier_service.DTO.PizzaOrderDTO;
import com.mentoring.courier_service.service.PizzaDeliveringService;
import com.mentoring.courier_service.utils.JsonConvertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PizzaOrderConsumer {
    private final PizzaDeliveringService pizzaDeliveringService;
    private final JsonConvertor jsonConvertor;

    @KafkaListener(topics = "${topics.consuming.fetch-orders-ready}")
    public void onPizzaCooked(List<ConsumerRecord<String, String>> records, Acknowledgment acks) {
        String ordersJson = records.stream()
                .map(ConsumerRecord::value)
                .collect(Collectors.joining(",", "[", "]"));

        log.info("Receiving pizza orders to delivery. Pizza orders: {}", ordersJson);

        List<PizzaOrderDTO> pizzaOrders = jsonConvertor.deserializeListItems(ordersJson, PizzaOrderDTO.class);

        pizzaOrders.forEach(pizzaDeliveringService::deliveryPizza);
        acks.acknowledge();
    }
}


