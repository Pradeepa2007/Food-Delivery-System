package com.food.delivery.system.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    private String street;
    private String city;
    private String state;
    private Integer zipcode;
}
