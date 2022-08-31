package com.mentoring.client_service.service;

import com.mentoring.client_service.model.Client;
import com.mentoring.client_service.model.OrderStatus;
import com.mentoring.client_service.model.PizzaOrder;
import com.mentoring.client_service.repository.ClientRepository;
import com.mentoring.client_service.repository.PizzaOrderRepository;
import com.mentoring.client_service.utils.JsonConvertorImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PizzaOrderServiceImpl implements PizzaOrderService {
    private final PizzaOrderRepository pizzaOrderRepo;
    private final ClientRepository clientRepo;
    private final JsonConvertorImpl jsonConvertor;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Environment env;

    @Override
    public void createPizzaOrder(PizzaOrder pizzaOrder) {
        Objects.requireNonNull(pizzaOrder);
        pizzaOrder.setStatus(OrderStatus.ORDER_CREATED);
        PizzaOrder saved = pizzaOrderRepo.save(pizzaOrder);
        kafkaTemplate.send(env.getProperty("topics.producing.send-pizza-order"), jsonConvertor.serializeToJson(saved));
        log.info("Create a new pizza order. Order: {}", saved);
    }

    @Override
    public PizzaOrder getPizzaOrder(Long id) {
        Objects.requireNonNull(id);
        return pizzaOrderRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find order by this id. Id = %d".formatted(id)));
    }

    @Override
    public PizzaOrder updatePizzaOrder(PizzaOrder pizzaOrder) {
        Objects.requireNonNull(pizzaOrder);
        log.info("Pizza order status was updated. Status = {}. Pizza order: {}", pizzaOrder.getStatus(), pizzaOrder);
        return pizzaOrderRepo.saveAndFlush(pizzaOrder);
    }

    @Override
    public List<PizzaOrder> getAllPizzaOrders(Long id) {
        Objects.requireNonNull(id);
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find client by id = %d".formatted(id)));
        client.getOrders().size();
        return client.getOrders();
    }
}
