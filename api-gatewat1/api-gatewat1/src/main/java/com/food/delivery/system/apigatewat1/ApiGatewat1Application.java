package com.food.delivery.system.apigatewat1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewat1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewat1Application.class, args);
	}

}
