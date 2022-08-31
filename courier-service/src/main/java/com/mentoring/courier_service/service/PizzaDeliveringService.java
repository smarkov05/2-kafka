package com.mentoring.courier_service.service;

import com.mentoring.courier_service.DTO.PizzaOrderDTO;

public interface PizzaDeliveringService {
    void deliveryPizza(PizzaOrderDTO pizzaOrderDTO);
}
