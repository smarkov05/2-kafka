package com.mentoring.client_service.repository;

import com.mentoring.client_service.model.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Long> {
}
