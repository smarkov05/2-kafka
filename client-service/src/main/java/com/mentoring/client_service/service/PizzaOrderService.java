package com.mentoring.client_service.service;

import com.mentoring.client_service.model.PizzaOrder;

import java.util.List;

public interface PizzaOrderService {
    void createPizzaOrder(PizzaOrder pizzaOrder);

    PizzaOrder getPizzaOrder(Long id);

    PizzaOrder updatePizzaOrder(PizzaOrder pizzaOrder);

    List<PizzaOrder> getAllPizzaOrders(Long clientId);
}
