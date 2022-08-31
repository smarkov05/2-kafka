package com.mentoring.palmetto_service.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PizzaOrderDTO {
    private Long id;
    private String name;
    private Integer quantity;
    private OrderStatus status;
    @JsonProperty("client")
    private ClientDTO clientDTO;
}
