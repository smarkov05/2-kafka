package com.mentoring.courier_service.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDTO {

    private Long id;
    private String fullname;
    private String phone;
    private String address;
    @JsonIgnore
    @ToString.Exclude
    private List<PizzaOrderDTO> orders;
}
