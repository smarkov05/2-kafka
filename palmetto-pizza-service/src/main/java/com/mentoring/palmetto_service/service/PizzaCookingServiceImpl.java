package com.mentoring.palmetto_service.service;

import com.mentoring.palmetto_service.DTO.OrderStatus;
import com.mentoring.palmetto_service.DTO.PizzaOrderDTO;
import com.mentoring.palmetto_service.utils.JsonConvertor;
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
public class PizzaCookingServiceImpl implements PizzaCookingService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonConvertor jsonConvertor;
    private final Environment env;

    @SneakyThrows
    @Async
    @Override
    public void cookPizza(PizzaOrderDTO pizzaOrderDTO) {
        log.info("Start cooking pizza by order:{}", pizzaOrderDTO);
        pizzaOrderDTO.setStatus(OrderStatus.ORDER_PREPARED);
        String pizzaOrderJson = jsonConvertor.serializeToJson(pizzaOrderDTO);
        Thread.sleep(5000L);
        kafkaTemplate.send(env.getProperty("topics.producing.change-order-status-notification"), pizzaOrderJson);
        log.info("Pizza has cooked. Waiting for courier delivery");
    }
}
