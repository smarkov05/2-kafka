package com.mentoring.courier_service.service;

import com.mentoring.courier_service.DTO.OrderStatus;
import com.mentoring.courier_service.DTO.PizzaOrderDTO;
import com.mentoring.courier_service.utils.JsonConvertor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PizzaDeliveringServiceImpl implements PizzaDeliveringService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonConvertor jsonConvertor;
    private final Environment env;

    @SneakyThrows
    @Async
    @Override
    public void deliveryPizza(PizzaOrderDTO pizzaOrderDTO) {
        log.info("Got pizza order to delivery. Pizza order:{}", pizzaOrderDTO);
        Thread.sleep(10_000L);
        pizzaOrderDTO.setStatus(OrderStatus.ORDER_DELIVERED);
        String pizzaOrderJson = jsonConvertor.serializeToJson(pizzaOrderDTO);
        kafkaTemplate.send(env.getProperty("topics.producing.send-delivery-notification"), pizzaOrderJson);
        log.info("Pizza was delivered to customer!");
    }
}
