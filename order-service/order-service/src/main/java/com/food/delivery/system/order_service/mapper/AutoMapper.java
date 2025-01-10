package com.food.delivery.system.order_service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoMapper {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
