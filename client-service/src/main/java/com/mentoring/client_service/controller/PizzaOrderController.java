package com.mentoring.client_service.controller;

import com.mentoring.client_service.model.Client;
import com.mentoring.client_service.model.PizzaOrder;
import com.mentoring.client_service.service.ClientService;
import com.mentoring.client_service.service.PizzaOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class PizzaOrderController {

    private final PizzaOrderService pizzaOrderService;
    private final ClientService clientService;

    @GetMapping()
    public PizzaOrder getOrder(@RequestParam("id") String id) {
        return pizzaOrderService.getPizzaOrder(Long.parseLong(id));
    }

    @GetMapping("/{client_id}")
    public List<PizzaOrder> getAllOrders(@PathVariable("client_id") String id) {
        return pizzaOrderService.getAllPizzaOrders(Long.parseLong(id));
    }

    @PostMapping
    public void createEvent(@RequestBody PizzaOrder pizzaOrder) {
        pizzaOrderService.createPizzaOrder(pizzaOrder);
    }

    @GetMapping("/client")
    public Client getClient(@RequestParam("id") Long id) {
        return clientService.getClient(id);
    }

}

