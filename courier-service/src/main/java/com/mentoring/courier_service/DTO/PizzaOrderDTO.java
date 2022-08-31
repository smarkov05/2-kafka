package com.mentoring.courier_service.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PizzaOrderDTO {
    private Long id;
    private String name;
    private Integer quantity;
    private OrderStatus status;
    @JsonProperty("client")
    private ClientDTO clientDTO;
}
